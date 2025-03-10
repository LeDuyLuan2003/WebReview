package com.example.reviewweb_backend.service;

import com.example.reviewweb_backend.dto.CommentDTO;
import com.example.reviewweb_backend.exception.NotFoundException;
import com.example.reviewweb_backend.mapper.CommentMapper;
import com.example.reviewweb_backend.model.Comment;
import com.example.reviewweb_backend.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.validation.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;
    private final Logger logger = LoggerFactory.getLogger(CommentService.class);

    public List<CommentDTO> getCommentsByReviewId(String reviewId) {
        return commentRepository.findByReviewId(reviewId).stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CommentDTO addComment(@Valid CommentDTO commentDTO) {
        Comment comment = commentMapper.toEntity(commentDTO);
        commentRepository.save(comment);
        logger.info("Thêm bình luận thành công: {}", commentDTO.getContent());
        return commentMapper.toDTO(comment);
    }

    public CommentDTO updateComment(String id, @Valid CommentDTO commentDTO) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Bình luận không tồn tại!"));

        existingComment.setContent(commentDTO.getContent());
        commentRepository.save(existingComment);
        logger.info("Cập nhật bình luận thành công: {}", id);
        return commentMapper.toDTO(existingComment);
    }

    public void deleteComment(String id) {
        if (!commentRepository.existsById(id)) {
            throw new NotFoundException("Bình luận không tồn tại!");
        }
        commentRepository.deleteById(id);
        logger.info("Xóa bình luận thành công: {}", id);
    }
}
