package com.example.reviewweb_backend.repository;

import com.example.reviewweb_backend.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String> {
    // Tìm kiếm vai trò theo tên
    Optional<Role> findByName(String name);
}
