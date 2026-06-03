package com.auth_service.spring.controller;


import com.auth_service.spring.dto.request.TenantFeatureRequest;
import com.auth_service.spring.dto.response.TenantFeatureResponse;
import com.auth_service.spring.service.inter.TenantFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenant-features")
@RequiredArgsConstructor
public class TenantFeatureController {

    private final TenantFeatureService service;

    @PostMapping
    public ResponseEntity<TenantFeatureResponse> create(
            @RequestBody TenantFeatureRequest request) {

        return ResponseEntity.ok(
                service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TenantFeatureResponse> update(
            @PathVariable Long id,
            @RequestBody TenantFeatureRequest request) {

        return ResponseEntity.ok(
                service.update(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantFeatureResponse> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<TenantFeatureResponse>>
    getAll() {

        return ResponseEntity.ok(
                service.getAll());
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<TenantFeatureResponse>>
    getByTenant(
            @PathVariable Long tenantId) {

        return ResponseEntity.ok(
                service.getByTenant(tenantId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}