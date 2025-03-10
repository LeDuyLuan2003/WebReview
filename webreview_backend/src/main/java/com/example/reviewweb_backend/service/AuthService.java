package com.example.reviewweb_backend.service;

import com.example.reviewweb_backend.dto.AuthDTO;
import com.example.reviewweb_backend.dto.UserDTO;
import com.example.reviewweb_backend.exception.DuplicateException;
import com.example.reviewweb_backend.exception.NotFoundException;
import com.example.reviewweb_backend.mapper.UserMapper;
import com.example.reviewweb_backend.model.User;
import com.example.reviewweb_backend.repository.UserRepository;
import jakarta.validation.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public UserDTO register(@Valid AuthDTO authDTO) {
        if (userRepository.existsByEmail(authDTO.getEmail())) {
            throw new DuplicateException("Email đã tồn tại!");
        }

        User newUser = User.builder()
                .email(authDTO.getEmail())
                .password(passwordEncoder.encode(authDTO.getPassword()))
                .role("user")
                .build();
        userRepository.save(newUser);

        logger.info("User đăng ký thành công: {}", authDTO.getEmail());
        return userMapper.toDTO(newUser);
    }

    public UserDTO login(@Valid AuthDTO authDTO) {
        User user = userRepository.findByEmail(authDTO.getEmail())
                .orElseThrow(() -> new NotFoundException("Email không tồn tại!"));

        if (!passwordEncoder.matches(authDTO.getPassword(), user.getPassword())) {
            throw new NotFoundException("Mật khẩu không đúng!");
        }

        logger.info("User đăng nhập thành công: {}", authDTO.getEmail());
        return userMapper.toDTO(user);
    }
}
