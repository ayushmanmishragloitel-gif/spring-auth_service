package com.auth_service.spring.service.impl;


import com.auth_service.spring.dto.mapper.UserMapper;
import com.auth_service.spring.dto.request.AuthLoginRequest;
import com.auth_service.spring.dto.request.UserRequest;
import com.auth_service.spring.dto.response.AuthResponse;
import com.auth_service.spring.dto.response.UserResponse;
import com.auth_service.spring.entity.MasTenancy;
import com.auth_service.spring.entity.OrganizationUnit;
import com.auth_service.spring.entity.Users;
import com.auth_service.spring.enums.Status;
import com.auth_service.spring.exception.BadRequestException;
import com.auth_service.spring.exception.ResourceAlreadyExistsException;
import com.auth_service.spring.exception.ResourceNotFoundException;
import com.auth_service.spring.repository.MasTenancyRepository;
import com.auth_service.spring.repository.OrganizationUnitRepository;
import com.auth_service.spring.repository.UsersRepository;
import com.auth_service.spring.security.JwtService;
import com.auth_service.spring.service.inter.UserService;
import com.auth_service.spring.util.PasswordHasher;
import com.auth_service.spring.util.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
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

//    public AuthResponse register(UserRequest request) {
//
//        if (usersRepository.existsByUsername(request.getUsername())) {
//            throw new RuntimeException("Username already exists");
//        }
//
//        MasTenancy tenant = tenancyRepository.findById(request.getTenantId())
//                .orElseThrow(() -> new RuntimeException("Tenant not found"));
//
//        OrganizationUnit organizationUnit = null;
//
//        if (request.getOrgUnitId() != null) {
//            organizationUnit = organizationUnitRepository.findById(request.getOrgUnitId())
//                    .orElseThrow(() -> new RuntimeException("Organization unit not found"));
//        }
//
//        Users user = Users.builder()
//                .tenant(tenant)
//                .username(request.getUsername())
//                .passwordHash(passwordEncoder.encode(request.getPassword()))
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .email(request.getEmail())
//                .mobile(request.getMobile())
//                .organizationUnit(organizationUnit)
//                .status(Status.ACTIVE)
//                .build();
//
//        Users savedUser = usersRepository.save(user);
//
//        String token = jwtService.generateToken(savedUser);
//
//        return buildAuthResponse(savedUser, token);
//    }

    public AuthResponse login(AuthLoginRequest request) {

        Users user = usersRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BadRequestException("Invalid username or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new BadRequestException("Invalid username or password");
        }

        if (user.getStatus() != Status.ACTIVE) {
            throw new BadRequestException("User is not active");
        }

        String token = jwtService.generateToken(user);

        return buildAuthResponse(user, token);
    }

    private AuthResponse buildAuthResponse(Users user, String token) {
        return AuthResponse.builder()
                .token(token)
                .userId(user.getUserId())
                .tenantId(user.getTenant().getTenantId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .status(user.getStatus())
                .build();
    }

}