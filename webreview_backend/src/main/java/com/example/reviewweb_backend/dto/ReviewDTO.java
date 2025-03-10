package com.example.reviewweb_backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {

    private String id;                  // ID của review
    private String contentId;           // ID của nội dung được đánh giá
    private String userId;              // ID của người dùng đánh giá
    private int rating;                 // Điểm đánh giá (1 - 10)
    private String comment;             // Bình luận của người dùng
    private LocalDateTime createdAt;    // Ngày đánh giá
}
