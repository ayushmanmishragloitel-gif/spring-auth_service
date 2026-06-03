package com.auth_service.spring.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleFeatureUpdateRequest {

    private Boolean canView;

    private Boolean canCreate;

    private Boolean canUpdate;

    private Boolean canDelete;

    private Boolean canApprove;

    private Boolean canReject;

    private Boolean canExport;
}