package com.auth_service.spring.dto.response;
import com.auth_service.spring.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {

    private Long userId;

    private Long tenantId;

    private String tenantName;

    private Long orgUnitId;

    private String orgUnitName;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String mobile;

    private Status status;
}