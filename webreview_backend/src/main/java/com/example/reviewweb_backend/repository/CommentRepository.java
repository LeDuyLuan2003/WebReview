package com.example.reviewweb_backend.repository;

import com.example.reviewweb_backend.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findByReviewId(String reviewId);   // Lấy tất cả bình luận của một review
    void deleteById(String id);                      // Xóa bình luận theo ID (admin dùng)
}
