package com.auth_service.spring.dto.mapper;


import com.auth_service.spring.dto.response.TenantFeatureResponse;
import com.auth_service.spring.entity.TenantFeature;
import org.springframework.stereotype.Component;

@Component
public class TenantFeatureMapper {

    public TenantFeatureResponse toDto(
            TenantFeature entity) {

        return TenantFeatureResponse.builder()
                .id(entity.getId())

                .tenantId(entity.getTenant().getTenantId())
                .tenantCode(entity.getTenant().getTenantCode())
                .tenantName(entity.getTenant().getTenantName())

                .featureId(entity.getFeature().getFeatureId())
                .featureCode(entity.getFeature().getFeatureCode())
                .featureName(entity.getFeature().getFeatureName())

                .isEnabled(entity.getIsEnabled())
                .build();
    }
}