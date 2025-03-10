package com.example.reviewweb_backend.repository;

import com.example.reviewweb_backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);        // Tìm user theo email (để đăng nhập)
    boolean existsByEmail(String email);             // Kiểm tra email đã tồn tại chưa (để đăng ký)
    void deleteById(String id);                      // Xóa user theo ID (admin dùng)
}
