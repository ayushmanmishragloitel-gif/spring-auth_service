package com.auth_service.spring.service.impl;


import com.auth_service.spring.dto.mapper.UserRoleMapper;
import com.auth_service.spring.dto.request.UserRoleRequest;
import com.auth_service.spring.dto.response.UserRoleResponse;
import com.auth_service.spring.entity.Roles;
import com.auth_service.spring.entity.UserRole;
import com.auth_service.spring.entity.Users;
import com.auth_service.spring.exception.ResourceAlreadyExistsException;
import com.auth_service.spring.exception.ResourceNotFoundException;
import com.auth_service.spring.repository.RolesRepository;
import com.auth_service.spring.repository.UserRoleRepository;
import com.auth_service.spring.repository.UsersRepository;
import com.auth_service.spring.service.inter.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final UserRoleMapper mapper;

    @Override
    public UserRoleResponse assignRole(
            UserRoleRequest request) {

        Users user = usersRepository.findById(
                        request.getUserId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        Roles role = rolesRepository.findById(
                        request.getRoleId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found"));

        // Same Tenant Validation
        if (!user.getTenant().getTenantId()
                .equals(role.getTenant().getTenantId())) {

            throw new RuntimeException(
                    "User and Role belong to different tenants");
        }

        if (userRoleRepository
                .existsByUser_UserIdAndRole_RoleId(
                        request.getUserId(),
                        request.getRoleId())) {

            throw new ResourceAlreadyExistsException(
                    "Role already assigned to user");
        }

        UserRole userRole = UserRole.builder()
                .user(user)
                .role(role)
                .build();

        UserRole saved =
                userRoleRepository.save(userRole);

        return mapper.toDto(saved);
    }

    @Override
    public void removeRole(
            Long userRoleId) {

        UserRole userRole =
                userRoleRepository.findById(userRoleId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User role not found"));

        userRoleRepository.delete(userRole);
    }

    @Override
    public UserRoleResponse getById(
            Long userRoleId) {

        UserRole userRole =
                userRoleRepository.findById(userRoleId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "User role not found"));

        return mapper.toDto(userRole);
    }

    @Override
    public List<UserRoleResponse> getAll() {

        return userRoleRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<UserRoleResponse> getRolesByUser(
            Long userId) {

        return userRoleRepository.findByUser_UserId(userId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}