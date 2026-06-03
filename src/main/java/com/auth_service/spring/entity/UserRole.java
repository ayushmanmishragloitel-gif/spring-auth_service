package com.auth_service.spring.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "USER_ROLES",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_USER_ROLE",
                        columnNames = {"USER_ID", "ROLE_ID"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_roles_seq_gen")
    @SequenceGenerator(
            name = "user_roles_seq_gen",
            sequenceName = "USER_ROLES_SEQ",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Roles role;
}