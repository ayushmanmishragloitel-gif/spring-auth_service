package com.auth_service.spring.dto.mapper;


import com.auth_service.spring.dto.request.UserRequest;
import com.auth_service.spring.dto.response.UserResponse;
import com.auth_service.spring.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public Users toEntity(UserRequest request) {

        Users user = new Users();

        user.setUsername(request.getUsername());

        user.setFirstName(request.getFirstName());

        user.setLastName(request.getLastName());

        user.setEmail(request.getEmail());

        user.setMobile(request.getMobile());

        return user;
    }

    public UserResponse toDto(Users user) {

        return UserResponse.builder()

                .userId(user.getUserId())

                .tenantId(
                        user.getTenant() != null
                                ? user.getTenant().getTenantId()
                                : null
                )

                .tenantName(
                        user.getTenant() != null
                                ? user.getTenant().getTenantName()
                                : null
                )

                .orgUnitId(
                        user.getOrganizationUnit() != null
                                ? user.getOrganizationUnit().getOrgUnitId()
                                : null
                )

                .orgUnitName(
                        user.getOrganizationUnit() != null
                                ? user.getOrganizationUnit().getUnitName()
                                : null
                )

                .username(user.getUsername())

                .firstName(user.getFirstName())

                .lastName(user.getLastName())

                .email(user.getEmail())

                .mobile(user.getMobile())

                .status(user.getStatus())

                .build();
    }
}