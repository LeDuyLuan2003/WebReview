package com.example.ReviewWeb_Backend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter  // Tự động tạo Getter, Setter, toString, equals, và hashCode
@Builder  // Cung cấp Builder Pattern
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "roles")
public class Role {

    @Id
    private String id;
    private String name;  // Enum chứa các vai trò như "USER", "ADMIN"
}
