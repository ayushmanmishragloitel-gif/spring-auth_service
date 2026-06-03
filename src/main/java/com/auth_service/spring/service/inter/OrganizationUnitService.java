package com.auth_service.spring.service.inter;


import com.auth_service.spring.dto.request.OrganizationUnitRequest;
import com.auth_service.spring.dto.response.OrganizationUnitResponse;

import java.util.List;

public interface OrganizationUnitService {

    OrganizationUnitResponse create(
            OrganizationUnitRequest request);

    OrganizationUnitResponse update(
            Long orgUnitId,
            OrganizationUnitRequest request);

    OrganizationUnitResponse getById(
            Long orgUnitId);

    List<OrganizationUnitResponse> getAll();

    void delete(
            Long orgUnitId);
}