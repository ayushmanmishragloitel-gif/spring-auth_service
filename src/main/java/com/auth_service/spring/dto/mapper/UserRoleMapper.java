package com.auth_service.spring.dto.mapper;


import com.auth_service.spring.dto.response.UserRoleResponse;
import com.auth_service.spring.entity.UserRole;
import org.springframework.stereotype.Component;

@Component
public class UserRoleMapper {

    public UserRoleResponse toDto(UserRole entity) {

        return UserRoleResponse.builder()
                .id(entity.getId())

                .userId(entity.getUser().getUserId())
                .username(entity.getUser().getUsername())

                .roleId(entity.getRole().getRoleId())
                .roleCode(entity.getRole().getRoleCode())
                .roleName(entity.getRole().getRoleName())

                .build();
    }
}