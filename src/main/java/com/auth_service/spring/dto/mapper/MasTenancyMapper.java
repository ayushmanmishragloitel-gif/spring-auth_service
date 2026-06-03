package com.auth_service.spring.dto.mapper;

import com.auth_service.spring.dto.request.MasTenancyRequest;
import com.auth_service.spring.dto.response.MasTenancyResponse;
import com.auth_service.spring.entity.MasTenancy;
import org.springframework.stereotype.Component;

@Component
public class MasTenancyMapper {

    public MasTenancy toEntity(MasTenancyRequest request) {

        MasTenancy tenancy = new MasTenancy();

        tenancy.setTenantCode(request.getTenantCode());
        tenancy.setTenantName(request.getTenantName());

        return tenancy;
    }

    public void updateEntity(
            MasTenancy tenancy,
            MasTenancyRequest request
    ) {

        tenancy.setTenantCode(request.getTenantCode());
        tenancy.setTenantName(request.getTenantName());
    }

    public MasTenancyResponse toResponse(MasTenancy tenancy) {

        return MasTenancyResponse.builder()
                .tenantId(tenancy.getTenantId())
                .tenantCode(tenancy.getTenantCode())
                .tenantName(tenancy.getTenantName())
                .build();
    }
}