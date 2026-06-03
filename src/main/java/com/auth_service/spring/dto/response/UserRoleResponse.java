package com.auth_service.spring.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRoleResponse {

    private Long id;

    private Long userId;

    private String username;

    private Long roleId;

    private String roleCode;

    private String roleName;
}