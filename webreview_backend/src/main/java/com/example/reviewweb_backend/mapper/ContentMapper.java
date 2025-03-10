package com.example.reviewweb_backend.mapper;

import com.example.reviewweb_backend.dto.ContentDTO;
import com.example.reviewweb_backend.model.Content;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContentMapper {

    ContentDTO toDTO(Content content);        // Chuyển từ model sang DTO
    Content toEntity(ContentDTO contentDTO);  // Chuyển từ DTO sang model
}
