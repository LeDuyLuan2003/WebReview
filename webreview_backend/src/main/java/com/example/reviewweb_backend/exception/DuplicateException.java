package com.example.reviewweb_backend.exception;

import org.springframework.http.HttpStatus;

public class DuplicateException extends CustomException {

    public DuplicateException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
