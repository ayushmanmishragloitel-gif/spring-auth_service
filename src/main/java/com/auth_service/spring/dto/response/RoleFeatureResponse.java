package com.auth_service.spring.dto.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoleFeatureResponse {

    private Long id;

    private Long roleId;

    private String roleCode;

    private String roleName;

    private Long featureId;

    private String featureCode;

    private String featureName;

    private Boolean canView;

    private Boolean canCreate;

    private Boolean canUpdate;

    private Boolean canDelete;

    private Boolean canApprove;

    private Boolean canReject;

    private Boolean canExport;
}