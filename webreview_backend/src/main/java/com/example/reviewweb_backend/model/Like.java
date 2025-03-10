package com.example.reviewweb_backend.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Like {

    private String userId;             // ID của người dùng đã thích
    private LocalDateTime createdAt = LocalDateTime.now();  // Thời gian thích


}
