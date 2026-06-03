package com.auth_service.spring.service.inter;

import com.auth_service.spring.dto.request.MasTenancyRequest;
import com.auth_service.spring.dto.response.MasTenancyResponse;

import java.util.List;

public interface MasTenancyService {

    MasTenancyResponse create(MasTenancyRequest request);

    MasTenancyResponse update(Long tenantId, MasTenancyRequest request);

    MasTenancyResponse getById(Long tenantId);

    List<MasTenancyResponse> getAll();

    void delete(Long tenantId);
}