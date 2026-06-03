package com.auth_service.spring.service.impl;

import com.auth_service.spring.dto.mapper.OrganizationUnitMapper;
import com.auth_service.spring.dto.request.OrganizationUnitRequest;
import com.auth_service.spring.dto.response.OrganizationUnitResponse;
import com.auth_service.spring.entity.MasTenancy;
import com.auth_service.spring.entity.OrganizationUnit;
import com.auth_service.spring.enums.Status;
import com.auth_service.spring.exception.ResourceAlreadyExistsException;
import com.auth_service.spring.exception.ResourceNotFoundException;
import com.auth_service.spring.repository.MasTenancyRepository;
import com.auth_service.spring.repository.OrganizationUnitRepository;
import com.auth_service.spring.service.inter.OrganizationUnitService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrganizationUnitServiceImpl
        implements OrganizationUnitService {

    private final OrganizationUnitRepository organizationUnitRepository;
    private final MasTenancyRepository tenancyRepository;
    private final OrganizationUnitMapper mapper;

    @Override
    public OrganizationUnitResponse create(
            OrganizationUnitRequest request) {

        MasTenancy tenant =
                tenancyRepository.findById(request.getTenantId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Tenant not found"));

        if (organizationUnitRepository
                .existsByUnitCodeIgnoreCase(
                        request.getUnitCode())) {

            throw new ResourceAlreadyExistsException(
                    "Unit code already exists");
        }

        OrganizationUnit unit =
                mapper.toEntity(request);

        unit.setTenant(tenant);

        unit.setStatus(Status.ACTIVE);

        if (request.getParentUnitId() != null) {

            OrganizationUnit parent =
                    organizationUnitRepository
                            .findById(
                                    request.getParentUnitId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Parent unit not found"));

            if (!parent.getTenant()
                    .getTenantId()
                    .equals(
                            tenant.getTenantId())) {

                throw new RuntimeException(
                        "Parent unit belongs to different tenant");
            }

            unit.setParentUnit(parent);
        }

        OrganizationUnit saved =
                organizationUnitRepository.save(unit);

        return mapper.toDto(saved);
    }
    @Override
    public OrganizationUnitResponse update(
            Long orgUnitId,
            OrganizationUnitRequest request) {

        OrganizationUnit unit =
                organizationUnitRepository
                        .findById(orgUnitId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Organization unit not found"));

        unit.setUnitName(request.getUnitName());
        unit.setUnitType(request.getUnitType());

        OrganizationUnit updated =
                organizationUnitRepository.save(unit);

        return mapper.toDto(updated);
    }
    @Override
    public OrganizationUnitResponse getById(
            Long orgUnitId) {

        OrganizationUnit unit =
                organizationUnitRepository
                        .findById(orgUnitId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Organization unit not found"));

        return mapper.toDto(unit);
    }
    @Override
    public List<OrganizationUnitResponse> getAll() {

        return organizationUnitRepository
                .findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
    @Override
    public void delete(Long orgUnitId) {

        OrganizationUnit unit =
                organizationUnitRepository
                        .findById(orgUnitId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Organization unit not found"));

        unit.setStatus(Status.INACTIVE);

        organizationUnitRepository.save(unit);
    }}
