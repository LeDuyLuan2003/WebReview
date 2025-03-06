package com.example.ReviewWeb_Backend.repository;

import com.example.ReviewWeb_Backend.model.Role;
import com.example.ReviewWeb_Backend.model.enumRole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    // Tìm kiếm vai trò theo tên
    Optional<Role> findByName(enumRole name);
}
