package com.auth_service.spring.service.inter;


import com.auth_service.spring.dto.request.TenantFeatureRequest;
import com.auth_service.spring.dto.response.TenantFeatureResponse;

import java.util.List;

public interface TenantFeatureService {

    TenantFeatureResponse create(
            TenantFeatureRequest request);

    TenantFeatureResponse update(
            Long id,
            TenantFeatureRequest request);

    TenantFeatureResponse getById(
            Long id);

    List<TenantFeatureResponse> getAll();

    List<TenantFeatureResponse> getByTenant(
            Long tenantId);

    void delete(
            Long id);
}