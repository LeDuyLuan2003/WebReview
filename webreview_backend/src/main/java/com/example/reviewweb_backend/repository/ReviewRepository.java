package com.example.reviewweb_backend.repository;

import com.example.reviewweb_backend.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {

    List<Review> findByContentId(String contentId);  // Lấy tất cả đánh giá theo contentId
    List<Review> findByUserId(String userId);        // Lấy tất cả đánh giá của một user
    void deleteById(String id);                      // Xóa đánh giá theo ID (admin dùng)
}
