package com.auth_service.spring.repository;


import com.auth_service.spring.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository
        extends JpaRepository<Roles, Long> {

    boolean existsByTenant_TenantIdAndRoleCodeIgnoreCase(
            Long tenantId,
            String roleCode
    );
}