package com.auth_service.spring.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TenantFeatureResponse {

    private Long id;

    private Long tenantId;

    private String tenantCode;

    private String tenantName;

    private Long featureId;

    private String featureCode;

    private String featureName;

    private Boolean isEnabled;
}