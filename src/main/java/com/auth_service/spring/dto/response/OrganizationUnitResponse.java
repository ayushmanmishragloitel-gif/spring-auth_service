package com.auth_service.spring.dto.response;


import com.auth_service.spring.enums.Status;
import com.auth_service.spring.enums.UnitType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrganizationUnitResponse {

    private Long orgUnitId;

    private Long tenantId;

    private String tenantCode;

    private String tenantName;

    private String unitCode;

    private String unitName;

    private UnitType unitType;

    private Long parentUnitId;

    private String parentUnitName;

    private Status status;
}