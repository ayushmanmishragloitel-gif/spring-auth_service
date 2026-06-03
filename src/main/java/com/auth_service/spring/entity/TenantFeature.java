package com.auth_service.spring.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "TENANT_FEATURES",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_TENANT_FEATURE",
                        columnNames = {"TENANT_ID", "FEATURE_ID"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TenantFeature extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tenant_features_seq_gen")
    @SequenceGenerator(
            name = "tenant_features_seq_gen",
            sequenceName = "TENANT_FEATURES_SEQ",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TENANT_ID", nullable = false)
    private MasTenancy tenant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FEATURE_ID", nullable = false)
    private Feature feature;

    @Column(name = "IS_ENABLED", nullable = false)
    private Boolean isEnabled;
}