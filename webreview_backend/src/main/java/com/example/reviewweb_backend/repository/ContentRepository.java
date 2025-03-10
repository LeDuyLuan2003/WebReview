package com.example.reviewweb_backend.repository;

import com.example.reviewweb_backend.model.Content;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ContentRepository extends MongoRepository<Content, String> {

    List<Content> findByCategory(String category);               // Lọc nội dung theo category
    List<Content> findByTitleContaining(String keyword);          // Tìm kiếm nội dung theo từ khóa trong tiêu đề
    void deleteById(String id);                                  // Xóa nội dung theo ID (admin dùng)
}
