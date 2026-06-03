package com.auth_service.spring.repository;


import com.auth_service.spring.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRepository
        extends JpaRepository<UserRole, Long> {

    boolean existsByUser_UserIdAndRole_RoleId(
            Long userId,
            Long roleId
    );

    List<UserRole> findByUser_UserId(Long userId);

    List<UserRole> findByRole_RoleId(Long roleId);
}