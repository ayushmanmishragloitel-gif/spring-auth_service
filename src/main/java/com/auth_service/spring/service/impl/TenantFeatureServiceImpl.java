package com.auth_service.spring.service.impl;


import com.auth_service.spring.dto.mapper.TenantFeatureMapper;
import com.auth_service.spring.dto.request.TenantFeatureRequest;
import com.auth_service.spring.dto.response.TenantFeatureResponse;
import com.auth_service.spring.entity.Feature;
import com.auth_service.spring.entity.MasTenancy;
import com.auth_service.spring.entity.TenantFeature;
import com.auth_service.spring.exception.ResourceAlreadyExistsException;
import com.auth_service.spring.exception.ResourceNotFoundException;
import com.auth_service.spring.repository.FeatureRepository;
import com.auth_service.spring.repository.MasTenancyRepository;
import com.auth_service.spring.repository.TenantFeatureRepository;
import com.auth_service.spring.service.inter.TenantFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TenantFeatureServiceImpl
        implements TenantFeatureService {

    private final TenantFeatureRepository repository;
    private final MasTenancyRepository tenantRepository;
    private final FeatureRepository featureRepository;
    private final TenantFeatureMapper mapper;

    @Override
    public TenantFeatureResponse create(
            TenantFeatureRequest request) {

        MasTenancy tenant =
                tenantRepository.findById(
                                request.getTenantId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Tenant not found"));

        Feature feature =
                featureRepository.findById(
                                request.getFeatureId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Feature not found"));

        if (repository
                .existsByTenant_TenantIdAndFeature_FeatureId(
                        request.getTenantId(),
                        request.getFeatureId())) {

            throw new ResourceAlreadyExistsException(
                    "Feature already mapped to tenant");
        }

        TenantFeature entity =
                TenantFeature.builder()
                        .tenant(tenant)
                        .feature(feature)
                        .isEnabled(
                                request.getIsEnabled())
                        .build();

        return mapper.toDto(
                repository.save(entity));
    }

    @Override
    public TenantFeatureResponse update(
            Long id,
            TenantFeatureRequest request) {

        TenantFeature entity =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Tenant Feature not found"));

        entity.setIsEnabled(
                request.getIsEnabled());

        return mapper.toDto(
                repository.save(entity));
    }

    @Override
    public TenantFeatureResponse getById(
            Long id) {

        return mapper.toDto(
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Tenant Feature not found"))
        );
    }

    @Override
    public List<TenantFeatureResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public List<TenantFeatureResponse> getByTenant(
            Long tenantId) {

        return repository.findByTenant_TenantId(
                        tenantId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void delete(Long id) {

        TenantFeature entity =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Tenant Feature not found"));

        repository.delete(entity);
    }
}