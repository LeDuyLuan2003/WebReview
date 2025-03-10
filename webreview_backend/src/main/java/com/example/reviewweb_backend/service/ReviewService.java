package com.example.reviewweb_backend.service;

import com.example.reviewweb_backend.dto.ReviewDTO;
import com.example.reviewweb_backend.exception.NotFoundException;
import com.example.reviewweb_backend.mapper.ReviewMapper;
import com.example.reviewweb_backend.model.Review;
import com.example.reviewweb_backend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.validation.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    public List<ReviewDTO> getReviewsByContentId(String contentId) {
        return reviewRepository.findByContentId(contentId).stream()
                .map(reviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReviewDTO addReview(@Valid ReviewDTO reviewDTO) {
        Review review = reviewMapper.toEntity(reviewDTO);
        reviewRepository.save(review);
        logger.info("Thêm đánh giá thành công cho nội dung: {}", reviewDTO.getContentId());
        return reviewMapper.toDTO(review);
    }

    public ReviewDTO updateReview(String id, @Valid ReviewDTO reviewDTO) {
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Đánh giá không tồn tại!"));

        existingReview.setRating(reviewDTO.getRating());
        existingReview.setComment(reviewDTO.getComment());
        reviewRepository.save(existingReview);
        logger.info("Cập nhật đánh giá thành công: {}", id);
        return reviewMapper.toDTO(existingReview);
    }

    public void deleteReview(String id) {
        if (!reviewRepository.existsById(id)) {
            throw new NotFoundException("Đánh giá không tồn tại!");
        }
        reviewRepository.deleteById(id);
        logger.info("Xóa đánh giá thành công: {}", id);
    }
}
