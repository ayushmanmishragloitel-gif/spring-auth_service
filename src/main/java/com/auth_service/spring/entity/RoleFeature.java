package com.auth_service.spring.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "ROLE_FEATURES",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_ROLE_FEATURE",
                        columnNames = {"ROLE_ID", "FEATURE_ID"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleFeature extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_features_seq_gen")
    @SequenceGenerator(
            name = "role_features_seq_gen",
            sequenceName = "ROLE_FEATURES_SEQ",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Roles role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FEATURE_ID", nullable = false)
    private Feature feature;

    @Column(name = "CAN_VIEW")
    private Boolean canView;

    @Column(name = "CAN_CREATE")
    private Boolean canCreate;

    @Column(name = "CAN_UPDATE")
    private Boolean canUpdate;

    @Column(name = "CAN_DELETE")
    private Boolean canDelete;

    @Column(name = "CAN_APPROVE")
    private Boolean canApprove;

    @Column(name = "CAN_REJECT")
    private Boolean canReject;

    @Column(name = "CAN_EXPORT")
    private Boolean canExport;
}