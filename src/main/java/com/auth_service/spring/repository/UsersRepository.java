package com.auth_service.spring.repository;

import com.auth_service.spring.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository
        extends JpaRepository<Users, Long> {

    boolean existsByUsernameIgnoreCase(
            String username);

    boolean existsByEmailIgnoreCase(
            String email);
}