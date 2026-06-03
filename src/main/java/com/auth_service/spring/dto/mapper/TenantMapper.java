package com.auth_service.spring.dto.mapper;


import com.auth_service.spring.dto.request.TenantRequestDto;
import com.auth_service.spring.dto.response.TenantResponseDto;
import com.auth_service.spring.entity.MasTenancy;
import org.springframework.stereotype.Component;

@Component
public class TenantMapper {

    public MasTenancy toEntity(TenantRequestDto dto) {

        MasTenancy tenant = new MasTenancy();

        tenant.setTenantCode(dto.getTenantCode());
        tenant.setTenantName(dto.getTenantName());

        return tenant;
    }

    public TenantResponseDto toDto(MasTenancy tenant) {

        return TenantResponseDto.builder()
                .tenantId(tenant.getTenantId())
                .tenantCode(tenant.getTenantCode())
                .tenantName(tenant.getTenantName())
                .build();
    }
}