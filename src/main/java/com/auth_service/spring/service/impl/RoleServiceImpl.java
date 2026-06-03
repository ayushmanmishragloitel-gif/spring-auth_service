package com.auth_service.spring.service.impl;


import com.auth_service.spring.dto.mapper.RoleMapper;
import com.auth_service.spring.dto.request.RoleRequest;
import com.auth_service.spring.dto.response.RoleResponse;
import com.auth_service.spring.entity.MasTenancy;
import com.auth_service.spring.entity.Roles;
import com.auth_service.spring.enums.Status;
import com.auth_service.spring.exception.ResourceAlreadyExistsException;
import com.auth_service.spring.exception.ResourceNotFoundException;
import com.auth_service.spring.repository.MasTenancyRepository;
import com.auth_service.spring.repository.RolesRepository;
import com.auth_service.spring.service.inter.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RolesRepository rolesRepository;
    private final MasTenancyRepository tenancyRepository;
    private final RoleMapper mapper;

    @Override
    public RoleResponse create(RoleRequest request) {

        MasTenancy tenant =
                tenancyRepository.findById(
                                request.getTenantId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Tenant not found"));

        if (rolesRepository
                .existsByTenant_TenantIdAndRoleCodeIgnoreCase(
                        request.getTenantId(),
                        request.getRoleCode())) {

            throw new ResourceAlreadyExistsException(
                    "Role code already exists");
        }

        Roles role = mapper.toEntity(request);

        role.setTenant(tenant);

        Roles savedRole =
                rolesRepository.save(role);

        return mapper.toDto(savedRole);
    }

    @Override
    public RoleResponse update(
            Long roleId,
            RoleRequest request) {

        Roles role = rolesRepository.findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found"));

        role.setRoleName(request.getRoleName());
        role.setDescription(request.getDescription());

        Roles updatedRole =
                rolesRepository.save(role);

        return mapper.toDto(updatedRole);
    }

    @Override
    public RoleResponse updateStatus(
            Long roleId,
            Status status) {

        Roles role = rolesRepository.findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found"));

        role.setStatus(status);

        return mapper.toDto(
                rolesRepository.save(role)
        );
    }

    @Override
    public RoleResponse getById(
            Long roleId) {

        Roles role = rolesRepository.findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found"));

        return mapper.toDto(role);
    }

    @Override
    public List<RoleResponse> getAll() {

        return rolesRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void delete(
            Long roleId) {

        Roles role = rolesRepository.findById(roleId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found"));

        role.setStatus(Status.INACTIVE);

        rolesRepository.save(role);
    }
}