package com.example.ReviewWeb_Backend.service;

import com.example.ReviewWeb_Backend.model.Role;
import com.example.ReviewWeb_Backend.model.User;
import com.example.ReviewWeb_Backend.exception.CustomException;
import com.example.ReviewWeb_Backend.repository.RoleRepository;
import com.example.ReviewWeb_Backend.repository.UserRepository;
import com.example.ReviewWeb_Backend.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RoleRepository roleRepository;


    // Đăng ký người dùng mới
    public void registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new CustomException("Username already exists!", HttpStatus.BAD_REQUEST);
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new CustomException("Email already exists!", HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findByName("USER")
                .orElseThrow(() -> new CustomException("Role not found", HttpStatus.NOT_FOUND));

        user.setRole(role);  // Gán vai trò cho người dùng
        userRepository.save(user);
    }

    // Đăng nhập người dùng và tạo JWT Token
    public String loginUser(User user) {
        User existingUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new CustomException("Invalid email or password!", HttpStatus.UNAUTHORIZED));

        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new CustomException("Invalid email or password!", HttpStatus.UNAUTHORIZED);
        }

        return jwtTokenProvider.generateToken(existingUser.getEmail());
    }

    // Đăng nhập bằng Google
    public String loginWithGoogle(OidcUser oidcUser) {
        String email = oidcUser.getEmail();

        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    // Nếu không có, đăng ký tài khoản mới với email từ Google
                    Role role = roleRepository.findByName("USER")  // Tìm role bằng tên "USER"
                            .orElseThrow(() -> new CustomException("Role not found", HttpStatus.NOT_FOUND));

                    // Sử dụng @Builder để tạo đối tượng User
                    User newUser = User.builder()
                            .email(email)
                            .username(email)   // Hoặc lấy tên từ Google nếu có
                            .password("")      // Để trống vì không cần mật khẩu khi đăng nhập Google
                            .role(role)        // Gán role USER
                            .build();

                    return userRepository.save(newUser);  // Lưu người dùng mới vào CSDL
                });

        // Tạo JWT token và trả về cho người dùng
        return jwtTokenProvider.generateToken(user.getEmail());
    }


}
