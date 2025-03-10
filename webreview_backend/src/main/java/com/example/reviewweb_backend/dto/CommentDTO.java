package com.example.reviewweb_backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private String id;                  // ID của bình luận
    private String reviewId;            // ID của review chứa bình luận
    private String userId;              // ID của người bình luận
    private String content;             // Nội dung bình luận
    private LocalDateTime createdAt;    // Ngày bình luận
}
