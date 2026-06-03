package com.auth_service.spring.controller;


import com.auth_service.spring.dto.request.RoleRequest;
import com.auth_service.spring.dto.response.RoleResponse;
import com.auth_service.spring.enums.Status;
import com.auth_service.spring.service.inter.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService service;

    @PostMapping
    public ResponseEntity<RoleResponse> create(
            @RequestBody RoleRequest request) {

        return ResponseEntity.ok(
                service.create(request)
        );
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleResponse> update(
            @PathVariable Long roleId,
            @RequestBody RoleRequest request) {

        return ResponseEntity.ok(
                service.update(roleId, request)
        );
    }

    @PatchMapping("/{roleId}/status")
    public ResponseEntity<RoleResponse> updateStatus(
            @PathVariable Long roleId,
            @RequestParam Status status) {

        return ResponseEntity.ok(
                service.updateStatus(
                        roleId,
                        status
                )
        );
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleResponse> getById(
            @PathVariable Long roleId) {

        return ResponseEntity.ok(
                service.getById(roleId)
        );
    }

    @GetMapping
    public ResponseEntity<List<RoleResponse>> getAll() {

        return ResponseEntity.ok(
                service.getAll()
        );
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long roleId) {

        service.delete(roleId);

        return ResponseEntity.noContent().build();
    }
}