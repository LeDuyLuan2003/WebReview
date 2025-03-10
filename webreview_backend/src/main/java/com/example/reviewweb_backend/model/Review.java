package com.example.reviewweb_backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.constraints.*;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reviews")
public class Review {

    @Id
    private String id;
    private String contentId;   // ID của Content mà review này thuộc về
    private String userId;      // ID của người dùng đã đánh giá
    private int rating;         // Điểm đánh giá (1-10)
    private String comment;     // Nội dung đánh giá
    private LocalDateTime createdAt = LocalDateTime.now();

    // Lồng danh sách likes trực tiếp vào Review
    private List<Like> likes;

    // Lồng danh sách comments trực tiếp vào Review
    private List<Comment> comments;
}
