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

    public static void updateEntityFromRequest(
            Feature feature,
            FeatureUpdateRequest request,
            Feature parentFeature
    ) {

        if (request.getFeatureCode() != null) {
            feature.setFeatureCode(request.getFeatureCode());
        }

        if (request.getFeatureName() != null) {
            feature.setFeatureName(request.getFeatureName());
        }

        if (request.getFeatureType() != null) {
            feature.setFeatureType(request.getFeatureType());
        }

        if (request.getParentFeatureId() != null) {
            feature.setParentFeature(parentFeature);
        }

        if (request.getSlug() != null) {
            feature.setSlug(request.getSlug());
        }

        if (request.getIcon() != null) {
            feature.setIcon(request.getIcon());
        }

        if (request.getDisplayOrder() != null) {
            feature.setDisplayOrder(request.getDisplayOrder());
        }

        if (request.getStatus() != null) {
            feature.setStatus(request.getStatus());
        }
    }
}