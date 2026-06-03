package com.auth_service.spring.repository;

import com.auth_service.spring.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeatureRepository extends JpaRepository<Feature, Long> {

    Optional<Feature> findByFeatureCode(String featureCode);

    boolean existsByFeatureCode(String featureCode);
    boolean existsByParentFeature_FeatureIdAndIsDeletedFalse(Long featureId);

    boolean existsByFeatureName(String featureName);
    boolean existsByFeatureNameAndFeatureIdNot(String featureName, Long featureId);

    Optional<Feature> findByFeatureId(Long featureId);

    boolean existsByFeatureCodeAndFeatureIdNot(String featureCode, Long featureId);
}