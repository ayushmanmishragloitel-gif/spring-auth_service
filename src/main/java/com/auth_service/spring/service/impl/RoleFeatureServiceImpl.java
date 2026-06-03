package com.auth_service.spring.service.impl;


import com.auth_service.spring.dto.mapper.RoleFeatureMapper;
import com.auth_service.spring.dto.request.RoleFeatureCreateRequest;
import com.auth_service.spring.dto.request.RoleFeatureUpdateRequest;
import com.auth_service.spring.dto.response.RoleFeatureResponse;
import com.auth_service.spring.entity.Feature;
import com.auth_service.spring.entity.RoleFeature;
import com.auth_service.spring.entity.Roles;
import com.auth_service.spring.exception.ResourceAlreadyExistsException;
import com.auth_service.spring.exception.ResourceNotFoundException;
import com.auth_service.spring.repository.FeatureRepository;
import com.auth_service.spring.repository.RoleFeatureRepository;
import com.auth_service.spring.repository.RolesRepository;
import com.auth_service.spring.service.inter.RoleFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleFeatureServiceImpl
        implements RoleFeatureService {

    private final RoleFeatureRepository roleFeatureRepository;
    private final RolesRepository rolesRepository;
    private final FeatureRepository featureRepository;
    private final RoleFeatureMapper mapper;

    @Override
    public RoleFeatureResponse create(
            RoleFeatureCreateRequest request) {

        Roles role = rolesRepository.findById(
                        request.getRoleId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Role not found"));

        Feature feature = featureRepository.findById(
                        request.getFeatureId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Feature not found"));

        if (roleFeatureRepository
                .existsByRole_RoleIdAndFeature_FeatureId(
                        request.getRoleId(),
                        request.getFeatureId())) {

            throw new ResourceAlreadyExistsException(
                    "Feature already mapped to role");
        }

        RoleFeature entity = mapper.toEntity(request);

        entity.setRole(role);
        entity.setFeature(feature);

        RoleFeature saved =
                roleFeatureRepository.save(entity);

        return mapper.toDto(saved);
    }

    @Override
    public RoleFeatureResponse update(
            Long id,
            RoleFeatureUpdateRequest request) {

        RoleFeature entity =
                roleFeatureRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Role Feature not found"));

        entity.setCanView(request.getCanView());
        entity.setCanCreate(request.getCanCreate());
        entity.setCanUpdate(request.getCanUpdate());
        entity.setCanDelete(request.getCanDelete());
        entity.setCanApprove(request.getCanApprove());
        entity.setCanReject(request.getCanReject());
        entity.setCanExport(request.getCanExport());

        RoleFeature updated =
                roleFeatureRepository.save(entity);

        return mapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleFeatureResponse getById(Long id) {

        RoleFeature entity =
                roleFeatureRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Role Feature not found"));

        return mapper.toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleFeatureResponse> getAll() {

        return roleFeatureRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleFeatureResponse> getByRole(
            Long roleId) {

        return roleFeatureRepository
                .findByRole_RoleId(roleId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {

        RoleFeature entity =
                roleFeatureRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Role Feature not found"));

        roleFeatureRepository.delete(entity);
    }
}