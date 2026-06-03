package com.auth_service.spring.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(
        name = "WORKFLOW_TRANSITION_ROLE",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_TRANSITION_ROLE",
                        columnNames = {
                                "TRANSITION_ID",
                                "ROLE_ID"
                        }
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowTransitionRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workflow_transition_role_seq_gen")
    @SequenceGenerator(
            name = "workflow_transition_role_seq_gen",
            sequenceName = "WORKFLOW_TRANSITION_ROLE_SEQ",
            allocationSize = 1
    )
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRANSITION_ID", nullable = false)
    private WorkflowTransition transition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private Roles role;
}