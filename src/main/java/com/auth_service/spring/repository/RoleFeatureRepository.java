package com.auth_service.spring.repository;


import com.auth_service.spring.entity.RoleFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleFeatureRepository
        extends JpaRepository<RoleFeature, Long> {

    boolean existsByRole_RoleIdAndFeature_FeatureId(
            Long roleId,
            Long featureId
    );

    List<RoleFeature> findByRole_RoleId(
            Long roleId
    );
}