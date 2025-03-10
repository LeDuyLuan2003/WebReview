package com.example.reviewweb_backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comments")
public class Comment {

    @Id
    private String id;
    private String reviewId;    // ID của Review mà comment này thuộc về
    private String userId;      // ID của người dùng bình luận
    private String content;     // Nội dung bình luận
    private LocalDateTime createdAt = LocalDateTime.now();

    // Lồng danh sách likes trực tiếp vào Comment
    private List<Like> likes;
}
