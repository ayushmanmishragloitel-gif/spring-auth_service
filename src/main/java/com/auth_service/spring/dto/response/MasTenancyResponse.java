package com.auth_service.spring.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MasTenancyResponse {

    private Long tenantId;

    private String tenantCode;

    private String tenantName;
}
