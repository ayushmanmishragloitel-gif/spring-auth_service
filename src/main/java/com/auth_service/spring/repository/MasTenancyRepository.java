package com.auth_service.spring.repository;

import com.auth_service.spring.entity.MasTenancy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MasTenancyRepository extends JpaRepository<MasTenancy, Long> {

    Optional<MasTenancy> findByTenantCode(String tenantCode);

    boolean existsByTenantCode(String tenantCode);
}
