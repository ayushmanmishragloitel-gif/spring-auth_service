package com.auth_service.spring.controller;

import com.auth_service.spring.dto.request.FeatureRequest;
import com.auth_service.spring.dto.request.FeatureUpdateRequest;
import com.auth_service.spring.dto.response.FeatureResponse;
import com.auth_service.spring.service.inter.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/features")
@RequiredArgsConstructor
public class FeatureController {

    private final FeatureService featureService;

    @PostMapping
    public ResponseEntity<FeatureResponse> createFeature(
            @RequestBody FeatureRequest request
    ) {

        return new ResponseEntity<>(
                featureService.createFeature(request),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{featureId}")
    public ResponseEntity<FeatureResponse> updateFeature(
            @PathVariable Long featureId,
            @RequestBody FeatureUpdateRequest request
    ) {
        return ResponseEntity.ok(
                featureService.updateFeature(featureId, request)
        );
    }

    @GetMapping("/{featureId}")
    public ResponseEntity<FeatureResponse> getFeatureById(
            @PathVariable Long featureId
    ) {

        return ResponseEntity.ok(
                featureService.getFeatureById(featureId)
        );
    }

    @GetMapping
    public ResponseEntity<List<FeatureResponse>> getAllFeatures() {

        return ResponseEntity.ok(
                featureService.getAllFeatures()
        );
    }

    @DeleteMapping("/{featureId}")
    public ResponseEntity<String> deleteFeature(
            @PathVariable Long featureId
    ) {

        featureService.deleteFeature(featureId);

        return ResponseEntity.ok("Feature deleted successfully");
    }
}
