package com.auth_service.spring.controller;

import com.auth_service.spring.dto.request.MasTenancyRequest;
import com.auth_service.spring.dto.response.MasTenancyResponse;
import com.auth_service.spring.service.inter.MasTenancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenancies")
@RequiredArgsConstructor
public class MasTenancyController {

    private final MasTenancyService service;

    @PostMapping
    public ResponseEntity<MasTenancyResponse> create(
            @RequestBody MasTenancyRequest request
    ) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{tenantId}")
    public ResponseEntity<MasTenancyResponse> update(
            @PathVariable Long tenantId,
            @RequestBody MasTenancyRequest request
    ) {
        return ResponseEntity.ok(service.update(tenantId, request));
    }

    @GetMapping("/{tenantId}")
    public ResponseEntity<MasTenancyResponse> getById(
            @PathVariable Long tenantId
    ) {
        return ResponseEntity.ok(service.getById(tenantId));
    }

    @GetMapping
    public ResponseEntity<List<MasTenancyResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{tenantId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long tenantId
    ) {
        service.delete(tenantId);
        return ResponseEntity.noContent().build();
    }
}
