package com.auth_service.spring.controller;


import com.auth_service.spring.dto.request.RoleFeatureCreateRequest;
import com.auth_service.spring.dto.request.RoleFeatureUpdateRequest;
import com.auth_service.spring.dto.response.RoleFeatureResponse;
import com.auth_service.spring.service.inter.RoleFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role-features")
@RequiredArgsConstructor
public class RoleFeatureController {

    private final RoleFeatureService service;

    @PostMapping
    public ResponseEntity<RoleFeatureResponse> create(
            @RequestBody RoleFeatureCreateRequest request) {

        return ResponseEntity.ok(
                service.create(request)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleFeatureResponse> update(
            @PathVariable Long id,
            @RequestBody RoleFeatureUpdateRequest request) {

        return ResponseEntity.ok(
                service.update(id, request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleFeatureResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<RoleFeatureResponse>> getAll() {

        return ResponseEntity.ok(
                service.getAll()
        );
    }

    @GetMapping("/role/{roleId}")
    public ResponseEntity<List<RoleFeatureResponse>> getByRole(
            @PathVariable Long roleId) {

        return ResponseEntity.ok(
                service.getByRole(roleId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}