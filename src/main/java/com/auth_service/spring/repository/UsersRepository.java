package com.auth_service.spring.repository;

import com.auth_service.spring.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository
        extends JpaRepository<Users, Long> {

    boolean existsByUsernameIgnoreCase(
            String username);

    boolean existsByEmailIgnoreCase(
            String email);


    Optional<Users> findByUsername(String username);

    boolean existsByUsername(String username);
}