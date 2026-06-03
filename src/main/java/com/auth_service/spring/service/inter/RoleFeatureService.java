package com.auth_service.spring.service.inter;


import com.auth_service.spring.dto.request.RoleFeatureCreateRequest;
import com.auth_service.spring.dto.request.RoleFeatureUpdateRequest;
import com.auth_service.spring.dto.response.RoleFeatureResponse;

import java.util.List;

public interface RoleFeatureService {

    RoleFeatureResponse create(
            RoleFeatureCreateRequest request);

    RoleFeatureResponse update(
            Long id,
            RoleFeatureUpdateRequest request);

    RoleFeatureResponse getById(
            Long id);

    List<RoleFeatureResponse> getAll();

    List<RoleFeatureResponse> getByRole(
            Long roleId);

    void delete(
            Long id);
}