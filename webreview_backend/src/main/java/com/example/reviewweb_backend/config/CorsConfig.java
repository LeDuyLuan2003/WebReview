package com.example.reviewweb_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")        // Cho phép tất cả các API bắt đầu bằng /api/
                        .allowedOrigins("*")          // Cho phép tất cả các domain truy cập
                        .allowedMethods("*")          // Cho phép tất cả các phương thức HTTP
                        .allowedHeaders("*")          // Cho phép tất cả các header
                        .maxAge(3600);                // Thời gian cache CORS là 1 giờ
            }
        };
    }
}

