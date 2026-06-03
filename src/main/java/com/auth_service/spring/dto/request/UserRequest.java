package com.auth_service.spring.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

    private Long tenantId;

    private Long orgUnitId;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String mobile;
}
