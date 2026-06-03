package com.auth_service.spring.service.impl;


import com.auth_service.spring.dto.mapper.UserMapper;
import com.auth_service.spring.dto.request.UserRequest;
import com.auth_service.spring.dto.response.UserResponse;
import com.auth_service.spring.entity.MasTenancy;
import com.auth_service.spring.entity.OrganizationUnit;
import com.auth_service.spring.entity.Users;
import com.auth_service.spring.enums.Status;
import com.auth_service.spring.exception.ResourceAlreadyExistsException;
import com.auth_service.spring.exception.ResourceNotFoundException;
import com.auth_service.spring.repository.MasTenancyRepository;
import com.auth_service.spring.repository.OrganizationUnitRepository;
import com.auth_service.spring.repository.UsersRepository;
import com.auth_service.spring.service.inter.UserService;
import com.auth_service.spring.util.PasswordHasher;
import com.auth_service.spring.util.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final MasTenancyRepository tenancyRepository;
    private final OrganizationUnitRepository organizationUnitRepository;
    private final UserMapper mapper;
    private final PasswordGenerator passwordGenerator;
    private final PasswordHasher passwordHasher;

    @Override
    public UserResponse create(UserRequest request) {

        MasTenancy tenant = tenancyRepository.findById(
                        request.getTenantId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Tenant not found"));

        OrganizationUnit orgUnit =
                organizationUnitRepository.findById(
                                request.getOrgUnitId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Organization Unit not found"));

        if (!orgUnit.getTenant()
                .getTenantId()
                .equals(tenant.getTenantId())) {

            throw new RuntimeException(
                    "Organization Unit belongs to another tenant");
        }

        if (usersRepository.existsByUsernameIgnoreCase(
                request.getUsername())) {

            throw new ResourceAlreadyExistsException(
                    "Username already exists");
        }

        if (usersRepository.existsByEmailIgnoreCase(
                request.getEmail())) {

            throw new ResourceAlreadyExistsException(
                    "Email already exists");
        }

        Users user = mapper.toEntity(request);

        user.setTenant(tenant);

        user.setOrganizationUnit(orgUnit);

        String rawPassword = passwordGenerator.generate();
        user.setPasswordHash(passwordHasher.hash(rawPassword));
        user.setStatus(Status.ACTIVE);

        Users savedUser =
                usersRepository.save(user);

        return mapper.toDto(savedUser);
    }

    @Override
    public UserResponse update(
            Long userId,
            UserRequest request) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setMobile(request.getMobile());

        Users updatedUser =
                usersRepository.save(user);

        return mapper.toDto(updatedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse getById(Long userId) {

        Users user = usersRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        return mapper.toDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAll() {

        return usersRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

}
