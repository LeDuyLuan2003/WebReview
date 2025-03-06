package com.example.ReviewWeb_Backend.model;

public enum enumRole {
    USER,
    ADMIN;
    // Phương thức để chuyển chuỗi thành Enum
    public static enumRole fromString(String roleName) {
        for (enumRole role : enumRole.values()) {
            if (role.name().equalsIgnoreCase(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown role: " + roleName);
    }
}
