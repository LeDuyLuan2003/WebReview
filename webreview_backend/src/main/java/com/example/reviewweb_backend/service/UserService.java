package com.example.reviewweb_backend.service;

import com.example.reviewweb_backend.dto.UserDTO;
import com.example.reviewweb_backend.exception.DuplicateException;
import com.example.reviewweb_backend.exception.NotFoundException;
import com.example.reviewweb_backend.mapper.UserMapper;
import com.example.reviewweb_backend.model.User;
import com.example.reviewweb_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.validation.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getUserById(String id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new NotFoundException("User không tồn tại!"));
    }

    public UserDTO createUser(@Valid UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new DuplicateException("Email đã tồn tại!");
        }

        User user = userMapper.toEntity(userDTO);
        userRepository.save(user);
        logger.info("Thêm User thành công: {}", userDTO.getEmail());
        return userMapper.toDTO(user);
    }
    public UserDTO updateUser(String id, @Valid UserDTO userDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User không tồn tại!"));

        if (!user.getEmail().equals(userDTO.getEmail()) && userRepository.existsByEmail(userDTO.getEmail())) {
            throw new DuplicateException("Email đã tồn tại!");
        }

        user.setFullname(userDTO.getFullname());
        user.setEmail(userDTO.getEmail());
        user.setAvatar(userDTO.getAvatar());
        userRepository.save(user);

        logger.info("Cập nhật User thành công: {}", userDTO.getEmail());
        return userMapper.toDTO(user);
    }

    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException("User không tồn tại!");
        }
        userRepository.deleteById(id);
        logger.info("Xóa User thành công: {}", id);
    }
}
