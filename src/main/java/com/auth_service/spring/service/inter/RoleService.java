package com.auth_service.spring.service.inter;


import com.auth_service.spring.dto.request.RoleRequest;
import com.auth_service.spring.dto.response.RoleResponse;
import com.auth_service.spring.enums.Status;

import java.util.List;

public interface RoleService {

    RoleResponse create(RoleRequest request);

    RoleResponse update(
            Long roleId,
            RoleRequest request);

    RoleResponse updateStatus(
            Long roleId,
            Status status);

    RoleResponse getById(
            Long roleId);

    List<RoleResponse> getAll();

    void delete(
            Long roleId);
}