package com.example.ReviewWeb_Backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter    // Tự động tạo Getter, Setter, toString, equals, và hashCode
@Builder  // Cung cấp Builder Pattern
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class User {

    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private Role role;  // Tham chiếu đến collection "roles"
}
