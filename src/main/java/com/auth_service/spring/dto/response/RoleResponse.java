package com.auth_service.spring.dto.response;


import com.auth_service.spring.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoleResponse {

    private Long roleId;

    private Long tenantId;

    private String tenantCode;

    private String tenantName;

    private String roleCode;

    private String roleName;

    private String description;

    private Status status;
}