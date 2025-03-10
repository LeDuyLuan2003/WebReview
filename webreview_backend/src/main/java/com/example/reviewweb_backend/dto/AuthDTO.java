package com.example.reviewweb_backend.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {

    private String email;       // Email đăng nhập
    private String password;    // Mật khẩu đăng nhập (nếu có thể dùng @JsonIgnore)
}
