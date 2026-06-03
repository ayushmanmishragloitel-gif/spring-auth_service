package com.auth_service.spring.entity;

import com.auth_service.spring.enums.DataScopeType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "ROLE_DATA_SCOPE",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_ROLE_DATA_SCOPE",
                        columnNames = {"ROLE_ID"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDataScope extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_data_scope_seq_gen")
    @SequenceGenerator(
            name = "role_data_scope_seq_gen",
            sequenceName = "ROLE_DATA_SCOPE_SEQ",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Roles role;

    @Enumerated(EnumType.STRING)
    @Column(name = "SCOPE_TYPE", nullable = false)
    private DataScopeType scopeType;
}
