package com.auth_service.spring.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantRequestDto {

    @NotBlank(message = "Tenant code is required")
    private String tenantCode;

    @NotBlank(message = "Tenant name is required")
    private String tenantName;
}
