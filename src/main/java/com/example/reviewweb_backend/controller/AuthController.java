package com.example.reviewweb_backend.controller;

import com.example.reviewweb_backend.exception.CustomException;
import com.example.reviewweb_backend.model.User;
import com.example.reviewweb_backend.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    // Đăng ký người dùng thường
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        userService.registerUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    // Đăng nhập người dùng thường
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            String token = userService.loginUser(user);
            return ResponseEntity.ok(Map.of(
                    "token", "Bearer " + token,
                    "message", "Login successful"
            ));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(Map.of("error", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "An unexpected error occurred"));
        }
    }

    // Đăng nhập bằng Google
    @GetMapping("/oauth2/success")
    public ResponseEntity<String> loginWithGoogle(@AuthenticationPrincipal OidcUser oidcUser) {
        String token = userService.loginWithGoogle(oidcUser);
        return ResponseEntity.ok("Bearer " + token);
    }

}
