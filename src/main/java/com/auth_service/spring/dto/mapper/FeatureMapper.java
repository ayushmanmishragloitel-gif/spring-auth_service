package com.auth_service.spring.dto.mapper;

import com.auth_service.spring.dto.request.FeatureRequest;
import com.auth_service.spring.dto.request.FeatureUpdateRequest;
import com.auth_service.spring.dto.response.FeatureResponse;
import com.auth_service.spring.entity.Feature;

public class FeatureMapper {

    public static Feature toEntity(FeatureRequest request, Feature parentFeature) {

        return Feature.builder()
                .featureCode(request.getFeatureCode())
                .featureName(request.getFeatureName())
                .featureType(request.getFeatureType())
                .parentFeature(parentFeature)
                .slug(request.getSlug())
                .icon(request.getIcon())
                .displayOrder(request.getDisplayOrder())
                .status(request.getStatus())
                .build();
    }

    public static FeatureResponse toResponse(Feature feature) {

        return FeatureResponse.builder()
                .featureId(feature.getFeatureId())
                .featureCode(feature.getFeatureCode())
                .featureName(feature.getFeatureName())
                .featureType(feature.getFeatureType())
                .parentFeatureId(
                        feature.getParentFeature() != null
                                ? feature.getParentFeature().getFeatureId()
                                : null
                )
                .parentFeatureName(
                        feature.getParentFeature() != null
                                ? feature.getParentFeature().getFeatureName()
                                : null
                )
                .slug(feature.getSlug())
                .icon(feature.getIcon())
                .displayOrder(feature.getDisplayOrder())
                .status(feature.getStatus())
                .build();
    }
}