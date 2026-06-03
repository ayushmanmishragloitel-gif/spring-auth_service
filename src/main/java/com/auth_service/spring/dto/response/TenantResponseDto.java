package com.auth_service.spring.dto.response;



import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TenantResponseDto {

    private Long tenantId;

    private String tenantCode;

    private String tenantName;
}