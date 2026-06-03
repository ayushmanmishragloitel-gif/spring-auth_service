package com.auth_service.spring.entity;


import jakarta.persistence.*;
import lombok.*;
import com.auth_service.spring.enums.Status;

@Entity
@Table(name = "POLICIES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Policy extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policies_seq_gen")
    @SequenceGenerator(
            name = "policies_seq_gen",
            sequenceName = "POLICIES_SEQ",
            allocationSize = 1
    )
    @Column(name = "POLICY_ID")
    private Long policyId;

    @Column(name = "POLICY_CODE", nullable = false, unique = true)
    private String policyCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FEATURE_ID", nullable = false)
    private Feature feature;

    @Column(name = "EFFECT", nullable = false)
    private String effect; // ALLOW / DENY

    @Column(name = "PRIORITY", nullable = false)
    private Integer priority;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;
}