package com.example.ReviewWeb_Backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {

    @Id
    private String id;
    private enumRole name;  // Enum chứa các vai trò như "USER", "ADMIN"

    // Constructor
    public Role() {
    }

    public Role(String id, enumRole name) {
        this.id = id;
        this.name = name;
    }

    // Getters và Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public enumRole getName() {
        return name;
    }

    public void setName(enumRole name) {
        this.name = name;
    }
}
