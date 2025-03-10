package com.example.reviewweb_backend.mapper;

import com.example.reviewweb_backend.dto.CommentDTO;
import com.example.reviewweb_backend.model.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    CommentDTO toDTO(Comment comment);        // Chuyển từ model sang DTO
    Comment toEntity(CommentDTO commentDTO);  // Chuyển từ DTO sang model
}
