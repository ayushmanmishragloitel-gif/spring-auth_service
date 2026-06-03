package com.auth_service.spring.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasTenancyRequest {

    private String tenantCode;

    private String tenantName;
}
