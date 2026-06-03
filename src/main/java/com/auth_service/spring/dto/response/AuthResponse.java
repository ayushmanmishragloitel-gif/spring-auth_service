package com.auth_service.spring.dto.response;

import com.auth_service.spring.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthResponse {

    private String token;

    private Long userId;

    private Long tenantId;

    private String username;

    private String firstName;

    private String lastName;

    private Status status;
}