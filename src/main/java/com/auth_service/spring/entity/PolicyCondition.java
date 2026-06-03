package com.auth_service.spring.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "POLICY_CONDITIONS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolicyCondition extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "policy_conditions_seq_gen")
    @SequenceGenerator(
            name = "policy_conditions_seq_gen",
            sequenceName = "POLICY_CONDITIONS_SEQ",
            allocationSize = 1
    )
    @Column(name = "CONDITION_ID")
    private Long conditionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POLICY_ID", nullable = false)
    private Policy policy;

    @Column(name = "ATTRIBUTE_NAME", nullable = false)
    private String attributeName;

    @Column(name = "OPERATOR", nullable = false)
    private String operator;

    @Column(name = "ATTRIBUTE_VALUE", nullable = false)
    private String attributeValue;
}