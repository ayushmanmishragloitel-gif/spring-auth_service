package com.auth_service.spring.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MAS_TENANCY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MasTenancy extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mas_tenancy_seq_gen")
    @SequenceGenerator(
            name = "mas_tenancy_seq_gen",
            sequenceName = "MAS_TENANCY_SEQ",
            allocationSize = 1
    )
    @Column(name = "TENANT_ID")
    private Long tenantId;

    @Column(name = "TENANT_CODE", nullable = false, unique = true)
    private String tenantCode;

    @Column(name = "TENANT_NAME", nullable = false)
    private String tenantName;
}