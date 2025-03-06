package com.example.ReviewWeb_Backend.controller;

import com.example.ReviewWeb_Backend.model.User;
import com.example.ReviewWeb_Backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private  UserService userService;

    // Đăng ký người dùng thường
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    // Đăng nhập người dùng thường
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        String token = userService.loginUser(user);
        return ResponseEntity.ok("Bearer " + token);
    }

    // Đăng nhập bằng Google
    @GetMapping("/oauth2/success")
    public ResponseEntity<String> loginWithGoogle(@AuthenticationPrincipal OidcUser oidcUser) {
        String token = userService.loginWithGoogle(oidcUser);
        return ResponseEntity.ok("Bearer " + token);
    }
}
