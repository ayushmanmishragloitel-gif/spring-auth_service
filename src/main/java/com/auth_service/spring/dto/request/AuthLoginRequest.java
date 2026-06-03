package com.auth_service.spring.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthLoginRequest {

    private String username;

    private String password;
}
