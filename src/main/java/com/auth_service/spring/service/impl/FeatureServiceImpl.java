package com.auth_service.spring.service.impl;

import com.auth_service.spring.dto.mapper.FeatureMapper;
import com.auth_service.spring.dto.request.FeatureRequest;
import com.auth_service.spring.dto.request.FeatureUpdateRequest;
import com.auth_service.spring.dto.response.FeatureResponse;
import com.auth_service.spring.entity.Feature;
import com.auth_service.spring.enums.FeatureType;
import com.auth_service.spring.exception.ResourceAlreadyExistsException;
import com.auth_service.spring.exception.ResourceNotFoundException;
import com.auth_service.spring.repository.FeatureRepository;
import com.auth_service.spring.service.inter.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;

    @Override
    public FeatureResponse createFeature(FeatureRequest request) {

        validateCreateUniqueFields(request);

        Feature parentFeature = getParentFeature(request.getParentFeatureId());

        validateFeatureHierarchy(request, parentFeature);

        Feature feature = FeatureMapper.toEntity(request, parentFeature);

        return FeatureMapper.toResponse(
                featureRepository.save(feature)
        );
    }

    @Override
    public FeatureResponse updateFeature(Long featureId, FeatureUpdateRequest request) {

        Feature feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new ResourceNotFoundException("Feature not found"));

        if (request.getFeatureCode() != null
                && featureRepository.existsByFeatureCodeAndFeatureIdNot(
                request.getFeatureCode(),
                featureId
        )) {
            throw new ResourceAlreadyExistsException("Feature code already exists");
        }

        if (request.getFeatureName() != null
                && featureRepository.existsByFeatureNameAndFeatureIdNot(
                request.getFeatureName(),
                featureId
        )) {
            throw new ResourceAlreadyExistsException("Feature name already exists");
        }

        FeatureType finalFeatureType = request.getFeatureType() != null
                ? request.getFeatureType()
                : feature.getFeatureType();

        Long finalParentFeatureId = request.getParentFeatureId() != null
                ? request.getParentFeatureId()
                : feature.getParentFeature() != null
                  ? feature.getParentFeature().getFeatureId()
                  : null;

        String finalIcon = request.getIcon() != null
                ? request.getIcon()
                : feature.getIcon();

        Feature parentFeature = null;

        if (finalParentFeatureId != null) {
            parentFeature = featureRepository.findById(finalParentFeatureId)
                    .orElseThrow(() -> new ResourceNotFoundException("Parent feature not found"));
        }

        validateUpdateHierarchy(finalFeatureType, finalParentFeatureId, finalIcon, parentFeature);

        FeatureMapper.updateEntityFromRequest(feature, request, parentFeature);

        return FeatureMapper.toResponse(
                featureRepository.save(feature)
        );
    }

    @Override
    public FeatureResponse getFeatureById(Long featureId) {

        Feature feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new ResourceNotFoundException("Feature not found"));

        return FeatureMapper.toResponse(feature);
    }

    @Override
    public List<FeatureResponse> getAllFeatures() {

        return featureRepository.findAll()
                .stream()
                .map(FeatureMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFeature(Long featureId) {

        Feature feature = featureRepository.findById(featureId)
                .orElseThrow(() -> new ResourceNotFoundException("Feature not found"));

        boolean hasChild = featureRepository
                .existsByParentFeature_FeatureIdAndIsDeletedFalse(featureId);

        if (hasChild) {
            throw new ResourceAlreadyExistsException(
                    "Cannot delete feature because child features exist"
            );
        }

        /*
         * This will soft delete only if your entity has:
         *
         * @SQLDelete(sql = "UPDATE FEATURES SET IS_DELETED = true WHERE FEATURE_ID = ?")
         * @SQLRestriction("IS_DELETED = false")
         */
        featureRepository.delete(feature);
    }

    private void validateCreateUniqueFields(FeatureRequest request) {

        if (featureRepository.existsByFeatureCode(request.getFeatureCode())) {
            throw new ResourceAlreadyExistsException("Feature code already exists");
        }

        if (featureRepository.existsByFeatureName(request.getFeatureName())) {
            throw new ResourceAlreadyExistsException("Feature name already exists");
        }
    }

    private void validateUpdateUniqueFields(Long featureId, FeatureRequest request) {

        if (featureRepository.existsByFeatureCodeAndFeatureIdNot(
                request.getFeatureCode(),
                featureId
        )) {
            throw new ResourceAlreadyExistsException("Feature code already exists");
        }

        if (featureRepository.existsByFeatureNameAndFeatureIdNot(
                request.getFeatureName(),
                featureId
        )) {
            throw new ResourceAlreadyExistsException("Feature name already exists");
        }
    }

    private Feature getParentFeature(Long parentFeatureId) {

        if (parentFeatureId == null) {
            return null;
        }

        return featureRepository.findById(parentFeatureId)
                .orElseThrow(() -> new ResourceNotFoundException("Parent feature not found"));
    }

    private void validateFeatureHierarchy(FeatureRequest request, Feature parentFeature) {

        if (request.getFeatureType() == null) {
            throw new IllegalArgumentException("Feature type is required");
        }

        switch (request.getFeatureType()) {

            case MODULE -> validateModule(request);

            case SUBMODULE -> validateSubmodule(request, parentFeature);

            case PAGE -> validatePage(request, parentFeature);

            case ACTION -> validateAction(request, parentFeature);
        }
    }

    private void validateModule(FeatureRequest request) {

        if (request.getParentFeatureId() != null) {
            throw new IllegalArgumentException("MODULE should not have parentFeatureId");
        }

        // MODULE can have icon
        // MODULE can have slug
    }

    private void validateSubmodule(FeatureRequest request, Feature parentFeature) {

        if (request.getParentFeatureId() == null) {
            throw new IllegalArgumentException("SUBMODULE must have parentFeatureId");
        }

        if (parentFeature == null || parentFeature.getFeatureType() != FeatureType.MODULE) {
            throw new IllegalArgumentException("SUBMODULE parent must be MODULE");
        }

        // SUBMODULE can have icon
        // SUBMODULE can have slug
    }

    private void validatePage(FeatureRequest request, Feature parentFeature) {

        if (request.getParentFeatureId() == null) {
            throw new IllegalArgumentException("PAGE must have parentFeatureId");
        }

        if (parentFeature == null || parentFeature.getFeatureType() != FeatureType.SUBMODULE) {
            throw new IllegalArgumentException("PAGE parent must be SUBMODULE");
        }

        if (request.getIcon() != null && !request.getIcon().isBlank()) {
            throw new IllegalArgumentException("PAGE should not have icon");
        }

        // PAGE can have slug
    }

    private void validateAction(FeatureRequest request, Feature parentFeature) {

        if (request.getParentFeatureId() == null) {
            throw new IllegalArgumentException("ACTION must have parentFeatureId");
        }

        if (parentFeature == null || parentFeature.getFeatureType() != FeatureType.PAGE) {
            throw new IllegalArgumentException("ACTION parent must be PAGE");
        }

        if (request.getIcon() != null && !request.getIcon().isBlank()) {
            throw new IllegalArgumentException("ACTION should not have icon");
        }

        // ACTION usually does not need slug
    }

    private void validateUpdateHierarchy(
            FeatureType featureType,
            Long parentFeatureId,
            String icon,
            Feature parentFeature
    ) {

        if (featureType == null) {
            throw new IllegalArgumentException("Feature type is required");
        }

        switch (featureType) {

            case MODULE -> {
                if (parentFeatureId != null) {
                    throw new IllegalArgumentException("MODULE should not have parentFeatureId");
                }
            }

            case SUBMODULE -> {
                if (parentFeatureId == null) {
                    throw new IllegalArgumentException("SUBMODULE must have parentFeatureId");
                }

                if (parentFeature == null || parentFeature.getFeatureType() != FeatureType.MODULE) {
                    throw new IllegalArgumentException("SUBMODULE parent must be MODULE");
                }
            }

            case PAGE -> {
                if (parentFeatureId == null) {
                    throw new IllegalArgumentException("PAGE must have parentFeatureId");
                }

                if (parentFeature == null || parentFeature.getFeatureType() != FeatureType.SUBMODULE) {
                    throw new IllegalArgumentException("PAGE parent must be SUBMODULE");
                }

                if (icon != null && !icon.isBlank()) {
                    throw new IllegalArgumentException("PAGE should not have icon");
                }
            }

            case ACTION -> {
                if (parentFeatureId == null) {
                    throw new IllegalArgumentException("ACTION must have parentFeatureId");
                }

                if (parentFeature == null || parentFeature.getFeatureType() != FeatureType.PAGE) {
                    throw new IllegalArgumentException("ACTION parent must be PAGE");
                }

                if (icon != null && !icon.isBlank()) {
                    throw new IllegalArgumentException("ACTION should not have icon");
                }
            }
        }
    }
}