package com.auth_service.spring.service.inter;

import com.auth_service.spring.dto.request.AuthLoginRequest;
import com.auth_service.spring.dto.request.UserRequest;
import com.auth_service.spring.dto.response.AuthResponse;
import com.auth_service.spring.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse create(UserRequest request);

    UserResponse update(
            Long userId,
            UserRequest request);

    UserResponse getById(
            Long userId);

    List<UserResponse> getAll();

    AuthResponse login(AuthLoginRequest request);

//    AuthResponse register(UserRequest request);

}
