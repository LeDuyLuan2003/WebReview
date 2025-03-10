package com.example.reviewweb_backend.mapper;

import com.example.reviewweb_backend.dto.AuthDTO;
import com.example.reviewweb_backend.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    User toEntity(AuthDTO authDTO);  // Chuyển từ AuthDTO sang User
}
