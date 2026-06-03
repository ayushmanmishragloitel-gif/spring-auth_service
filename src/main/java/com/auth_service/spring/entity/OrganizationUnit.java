package com.auth_service.spring.entity;


import com.auth_service.spring.enums.UnitType;
import jakarta.persistence.*;
import lombok.*;
import com.auth_service.spring.enums.Status;

@Entity
@Table(name = "ORGANIZATION_UNIT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrganizationUnit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "org_unit_seq")
    @SequenceGenerator(
            name = "org_unit_seq",
            sequenceName = "ORG_UNIT_SEQ",
            allocationSize = 1
    )
    @Column(name = "ORG_UNIT_ID")
    private Long orgUnitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TENANT_ID", nullable = false)
    private MasTenancy tenant;

    @Column(name = "UNIT_CODE", nullable = false, unique = true)
    private String unitCode;

    @Column(name = "UNIT_NAME", nullable = false)
    private String unitName;

    @Enumerated(EnumType.STRING)
    @Column(name = "UNIT_TYPE", nullable = false)
    private UnitType unitType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_UNIT_ID")
    private OrganizationUnit parentUnit;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;
}