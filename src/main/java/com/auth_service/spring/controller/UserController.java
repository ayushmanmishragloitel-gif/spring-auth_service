package com.auth_service.spring.controller;

import com.auth_service.spring.dto.request.UserRequest;
import com.auth_service.spring.dto.response.UserResponse;
import com.auth_service.spring.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ResponseEntity<UserResponse> create(
            @RequestBody UserRequest request) {

        return ResponseEntity.ok(
                service.create(request)
        );
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> update(
            @PathVariable Long userId,
            @RequestBody UserRequest request) {

        return ResponseEntity.ok(
                service.update(userId, request)
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getById(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                service.getById(userId)
        );
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {

        return ResponseEntity.ok(
                service.getAll()
        );
    }

}