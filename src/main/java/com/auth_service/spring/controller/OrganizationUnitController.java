package com.auth_service.spring.controller;


import com.auth_service.spring.dto.request.OrganizationUnitRequest;
import com.auth_service.spring.dto.response.OrganizationUnitResponse;
import com.auth_service.spring.service.inter.OrganizationUnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organization-units")
@RequiredArgsConstructor
@Tag(name = "Organization Units", description = "APIs for managing organization units")
public class OrganizationUnitController {

    private final OrganizationUnitService service;

    @PostMapping
    @Operation(summary = "Create organization unit")
    public ResponseEntity<OrganizationUnitResponse> create(
            @Valid @RequestBody OrganizationUnitRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{orgUnitId}")
    @Operation(summary = "Update organization unit")
    public ResponseEntity<OrganizationUnitResponse> update(
            @PathVariable("orgUnitId") Long orgUnitId,
            @Valid @RequestBody OrganizationUnitRequest request) {

        return ResponseEntity.ok(service.update(orgUnitId, request));
    }

    @GetMapping("/{orgUnitId}")
    @Operation(summary = "Get organization unit by ID")
    public ResponseEntity<OrganizationUnitResponse> getById(
            @PathVariable("orgUnitId") Long orgUnitId) {

        return ResponseEntity.ok(service.getById(orgUnitId));
    }

    @GetMapping
    @Operation(summary = "Get all organization units")
    public ResponseEntity<List<OrganizationUnitResponse>> getAll() {

        return ResponseEntity.ok(service.getAll());
    }

    @DeleteMapping("/{orgUnitId}")
    @Operation(summary = "Delete organization unit")
    public ResponseEntity<Void> delete(
            @PathVariable("orgUnitId") Long orgUnitId) {

        service.delete(orgUnitId);

        return ResponseEntity.noContent().build();
    }
}
