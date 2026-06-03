package com.auth_service.spring.repository;


import com.auth_service.spring.entity.TenantFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TenantFeatureRepository
        extends JpaRepository<TenantFeature, Long> {

    boolean existsByTenant_TenantIdAndFeature_FeatureId(
            Long tenantId,
            Long featureId
    );

    Optional<TenantFeature>
    findByTenant_TenantIdAndFeature_FeatureId(
            Long tenantId,
            Long featureId
    );

    List<TenantFeature>
    findByTenant_TenantId(Long tenantId);
}