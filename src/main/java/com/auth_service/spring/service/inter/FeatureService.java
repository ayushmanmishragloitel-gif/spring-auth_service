package com.auth_service.spring.service.inter;

import com.auth_service.spring.dto.request.FeatureRequest;
import com.auth_service.spring.dto.request.FeatureUpdateRequest;
import com.auth_service.spring.dto.response.FeatureResponse;

import java.util.List;

public interface FeatureService {

    FeatureResponse createFeature(FeatureRequest request);

    FeatureResponse getFeatureById(Long featureId);

    List<FeatureResponse> getAllFeatures();

    void deleteFeature(Long featureId);

    FeatureResponse updateFeature(Long featureId, FeatureUpdateRequest request);
}