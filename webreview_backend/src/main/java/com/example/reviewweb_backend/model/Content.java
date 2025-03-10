package com.example.reviewweb_backend.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "contents")
public class Content {

    @Id
    private String id;

    @Indexed(unique = true)
    @NotBlank(message = "Tiêu đề không được để trống")
    @Size(max = 100, message = "Tiêu đề không được vượt quá 100 ký tự")
    private String title;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotBlank(message = "Danh mục không được để trống")
    private String category;
    @NotBlank(message = "The loai không được để trống")
    private String subcategory;
    private String image;
    private String trailer;
    private double averageRating;
    private LocalDateTime releaseDate;

    private List<Review> reviews;
}
