package com.auth_service.spring.dto.request;


import com.auth_service.spring.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {

    private Long tenantId;

    private String roleCode;

    private String roleName;

    private String description;

    private Status status;
}