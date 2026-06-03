package com.auth_service.spring.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRoleRequest {

    private Long userId;

    private Long roleId;
}