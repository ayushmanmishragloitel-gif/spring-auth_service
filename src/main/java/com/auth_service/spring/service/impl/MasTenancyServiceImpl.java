package com.auth_service.spring.service.impl;

import com.auth_service.spring.dto.mapper.MasTenancyMapper;
import com.auth_service.spring.dto.request.MasTenancyRequest;
import com.auth_service.spring.dto.response.MasTenancyResponse;
import com.auth_service.spring.entity.MasTenancy;
import com.auth_service.spring.exception.ResourceNotFoundException;
import com.auth_service.spring.repository.MasTenancyRepository;
import com.auth_service.spring.service.inter.MasTenancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MasTenancyServiceImpl implements MasTenancyService {

    private final MasTenancyRepository repository;
    private final MasTenancyMapper mapper;

    @Override
    public MasTenancyResponse create(MasTenancyRequest request) {

        if (repository.existsByTenantCode(request.getTenantCode())) {
            throw new ResourceNotFoundException("Tenant code already exists");
        }

        MasTenancy tenancy = mapper.toEntity(request);

        tenancy = repository.save(tenancy);

        return mapper.toResponse(tenancy);
    }

    @Override
    public MasTenancyResponse update(
            Long tenantId,
            MasTenancyRequest request
    ) {

        MasTenancy tenancy = repository.findById(tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found"));

        mapper.updateEntity(tenancy, request);

        tenancy = repository.save(tenancy);

        return mapper.toResponse(tenancy);
    }

    @Override
    public MasTenancyResponse getById(Long tenantId) {

        MasTenancy tenancy = repository.findById(tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found"));

        return mapper.toResponse(tenancy);
    }

    @Override
    public List<MasTenancyResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public void delete(Long tenantId) {

        MasTenancy tenancy = repository.findById(tenantId)
                .orElseThrow(() -> new ResourceNotFoundException("Tenant not found"));

        repository.delete(tenancy);
    }
}