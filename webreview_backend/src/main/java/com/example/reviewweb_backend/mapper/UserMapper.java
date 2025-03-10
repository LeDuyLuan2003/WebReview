package com.example.reviewweb_backend.mapper;

import com.example.reviewweb_backend.dto.UserDTO;
import com.example.reviewweb_backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);        // Chuyển từ model sang DTO
    User toEntity(UserDTO userDTO);  // Chuyển từ DTO sang model
}
