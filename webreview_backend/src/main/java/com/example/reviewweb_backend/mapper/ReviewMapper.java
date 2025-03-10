package com.example.reviewweb_backend.mapper;

import com.example.reviewweb_backend.dto.ReviewDTO;
import com.example.reviewweb_backend.model.Review;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewDTO toDTO(Review review);        // Chuyển từ model sang DTO
    Review toEntity(ReviewDTO reviewDTO);  // Chuyển từ DTO sang model
}
