package com.auth_service.spring.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TenantFeatureRequest {

    private Long tenantId;

    private Long featureId;

    private Boolean isEnabled;
}
