package com.example.reviewweb_backend.service;

import com.example.reviewweb_backend.dto.ContentDTO;
import com.example.reviewweb_backend.exception.NotFoundException;
import com.example.reviewweb_backend.mapper.ContentMapper;
import com.example.reviewweb_backend.model.Content;
import com.example.reviewweb_backend.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.validation.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;
    private final Logger logger = LoggerFactory.getLogger(ContentService.class);

    public List<ContentDTO> getAllContents() {
        return contentRepository.findAll().stream()
                .map(contentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ContentDTO getContentById(String id) {
        return contentRepository.findById(id)
                .map(contentMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("Nội dung không tồn tại!"));
    }

    public ContentDTO createContent(@Valid ContentDTO contentDTO) {
        Content content = contentMapper.toEntity(contentDTO);
        contentRepository.save(content);
        logger.info("Thêm nội dung thành công: {}", contentDTO.getTitle());
        return contentMapper.toDTO(content);
    }
    public ContentDTO updateContent(String id, @Valid ContentDTO contentDTO) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nội dung không tồn tại!"));

        content.setTitle(contentDTO.getTitle());
        content.setDescription(contentDTO.getDescription());
        content.setCategory(contentDTO.getCategory());
        content.setSubcategory(contentDTO.getSubcategory());
        content.setImage(contentDTO.getImage());
        content.setReleaseDate(contentDTO.getReleaseDate());
        contentRepository.save(content);

        logger.info("Cập nhật nội dung thành công: {}", contentDTO.getTitle());
        return contentMapper.toDTO(content);
    }

    public void deleteContent(String id) {
        if (!contentRepository.existsById(id)) {
            throw new NotFoundException("Nội dung không tồn tại!");
        }
        contentRepository.deleteById(id);
        logger.info("Xóa nội dung thành công: {}", id);
    }
}
