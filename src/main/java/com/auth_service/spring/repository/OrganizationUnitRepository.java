package com.auth_service.spring.repository;

import com.auth_service.spring.entity.OrganizationUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationUnitRepository
        extends JpaRepository<OrganizationUnit, Long> {

    boolean existsByUnitCodeIgnoreCase(String unitCode);

}