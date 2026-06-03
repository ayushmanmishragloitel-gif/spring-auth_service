package com.auth_service.spring.controller;


import com.auth_service.spring.dto.request.UserRoleRequest;
import com.auth_service.spring.dto.response.UserRoleResponse;
import com.auth_service.spring.service.inter.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-roles")
@RequiredArgsConstructor
public class UserRoleController {

    private final UserRoleService service;

    @PostMapping
    public ResponseEntity<UserRoleResponse> assignRole(
            @RequestBody UserRoleRequest request) {

        return ResponseEntity.ok(
                service.assignRole(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserRoleResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<UserRoleResponse>> getAll() {

        return ResponseEntity.ok(
                service.getAll()
        );
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserRoleResponse>> getRolesByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                service.getRolesByUser(userId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeRole(
            @PathVariable Long id) {

        service.removeRole(id);

        return ResponseEntity.noContent().build();
    }
}