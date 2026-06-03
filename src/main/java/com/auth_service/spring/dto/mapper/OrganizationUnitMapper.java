package com.auth_service.spring.dto.mapper;


import com.auth_service.spring.dto.request.OrganizationUnitRequest;
import com.auth_service.spring.dto.response.OrganizationUnitResponse;
import com.auth_service.spring.entity.OrganizationUnit;
import org.springframework.stereotype.Component;

@Component
public class OrganizationUnitMapper {

    public OrganizationUnit toEntity(
            OrganizationUnitRequest request) {

        OrganizationUnit unit = new OrganizationUnit();

        unit.setUnitCode(request.getUnitCode());
        unit.setUnitName(request.getUnitName());
        unit.setUnitType(request.getUnitType());

        return unit;
    }

    public OrganizationUnitResponse toDto(
            OrganizationUnit unit) {

        return OrganizationUnitResponse.builder()
                .orgUnitId(unit.getOrgUnitId())

                .tenantId(
                        unit.getTenant() != null
                                ? unit.getTenant().getTenantId()
                                : null
                )

                .tenantCode(
                        unit.getTenant() != null
                                ? unit.getTenant().getTenantCode()
                                : null
                )

                .tenantName(
                        unit.getTenant() != null
                                ? unit.getTenant().getTenantName()
                                : null
                )

                .unitCode(unit.getUnitCode())
                .unitName(unit.getUnitName())
                .unitType(unit.getUnitType())

                .parentUnitId(
                        unit.getParentUnit() != null
                                ? unit.getParentUnit().getOrgUnitId()
                                : null
                )

                .parentUnitName(
                        unit.getParentUnit() != null
                                ? unit.getParentUnit().getUnitName()
                                : null
                )

                .status(unit.getStatus())
                .build();
    }
}