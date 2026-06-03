package com.auth_service.spring.dto.request;


import com.auth_service.spring.enums.UnitType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationUnitRequest {

    @NotNull(message = "Tenant ID is required")
    private Long tenantId;

    @NotBlank(message = "Unit code is required")
    private String unitCode;

    @NotBlank(message = "Unit name is required")
    private String unitName;

    @NotNull(message = "Unit type is required")
    private UnitType unitType;

    private Long parentUnitId;
}
