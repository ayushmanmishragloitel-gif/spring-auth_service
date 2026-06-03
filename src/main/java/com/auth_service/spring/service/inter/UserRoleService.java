package com.auth_service.spring.service.inter;


import com.auth_service.spring.dto.request.UserRoleRequest;
import com.auth_service.spring.dto.response.UserRoleResponse;

import java.util.List;

public interface UserRoleService {

    UserRoleResponse assignRole(
            UserRoleRequest request);

    void removeRole(
            Long userRoleId);

    UserRoleResponse getById(
            Long userRoleId);

    List<UserRoleResponse> getAll();

    List<UserRoleResponse> getRolesByUser(
            Long userId);
}