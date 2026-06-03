package com.auth_service.spring.dto.mapper;


import com.auth_service.spring.dto.request.RoleFeatureCreateRequest;
import com.auth_service.spring.dto.response.RoleFeatureResponse;
import com.auth_service.spring.entity.RoleFeature;
import org.springframework.stereotype.Component;

@Component
public class RoleFeatureMapper {

    public RoleFeature toEntity(
            RoleFeatureCreateRequest request) {

        RoleFeature entity = new RoleFeature();

        entity.setCanView(request.getCanView());
        entity.setCanCreate(request.getCanCreate());
        entity.setCanUpdate(request.getCanUpdate());
        entity.setCanDelete(request.getCanDelete());
        entity.setCanApprove(request.getCanApprove());
        entity.setCanReject(request.getCanReject());
        entity.setCanExport(request.getCanExport());

        return entity;
    }

    public RoleFeatureResponse toDto(
            RoleFeature entity) {

        return RoleFeatureResponse.builder()

                .id(entity.getId())

                .roleId(entity.getRole().getRoleId())
                .roleCode(entity.getRole().getRoleCode())
                .roleName(entity.getRole().getRoleName())

                .featureId(entity.getFeature().getFeatureId())
                .featureCode(entity.getFeature().getFeatureCode())
                .featureName(entity.getFeature().getFeatureName())

                .canView(entity.getCanView())
                .canCreate(entity.getCanCreate())
                .canUpdate(entity.getCanUpdate())
                .canDelete(entity.getCanDelete())
                .canApprove(entity.getCanApprove())
                .canReject(entity.getCanReject())
                .canExport(entity.getCanExport())

                .build();
    }
}