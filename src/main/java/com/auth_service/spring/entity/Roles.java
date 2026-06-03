package com.auth_service.spring.entity;


import jakarta.persistence.*;
import lombok.*;
import com.auth_service.spring.enums.Status;

@Entity
@Table(
        name = "ROLES",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_ROLES_CODE",
                        columnNames = {"TENANT_ID", "ROLE_CODE"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Roles extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_seq_gen")
    @SequenceGenerator(
            name = "roles_seq_gen",
            sequenceName = "ROLES_SEQ",
            allocationSize = 1
    )
    @Column(name = "ROLE_ID")
    private Long roleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TENANT_ID", nullable = false)
    private MasTenancy tenant;

    @Column(name = "ROLE_CODE", nullable = false, length = 50)
    private String roleCode;

    @Column(name = "ROLE_NAME", nullable = false, length = 100)
    private String roleName;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 20)
    private Status status;
}