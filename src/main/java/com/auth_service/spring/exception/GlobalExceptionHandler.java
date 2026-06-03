package com.auth_service.spring.exception;

import com.auth_service.spring.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Void>> handleBadRequest(BadRequestException ex) {
        return new ResponseEntity<>(
                ApiResponse.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceAlreadyExists(ResourceAlreadyExistsException ex) {
        return new ResponseEntity<>(
                ApiResponse.error(HttpStatus.CONFLICT.value(), ex.getMessage()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(
                ApiResponse.error(HttpStatus.NOT_FOUND.value(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }
}
