package com.auth_service.spring.dto.mapper;


import com.auth_service.spring.dto.request.RoleRequest;
import com.auth_service.spring.dto.response.RoleResponse;
import com.auth_service.spring.entity.Roles;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public Roles toEntity(RoleRequest request) {

        Roles role = new Roles();

        role.setRoleCode(request.getRoleCode());
        role.setRoleName(request.getRoleName());
        role.setDescription(request.getDescription());
        role.setStatus(request.getStatus());

        return role;
    }

    public RoleResponse toDto(Roles role) {

        return RoleResponse.builder()
                .roleId(role.getRoleId())

                .tenantId(role.getTenant().getTenantId())
                .tenantCode(role.getTenant().getTenantCode())
                .tenantName(role.getTenant().getTenantName())

                .roleCode(role.getRoleCode())
                .roleName(role.getRoleName())
                .description(role.getDescription())
                .status(role.getStatus())
                .build();
    }
}