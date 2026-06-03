package com.auth_service.spring.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "WORKFLOW_TRANSITION",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_WORKFLOW_TRANSITION",
                        columnNames = {
                                "WORKFLOW_ID",
                                "FROM_STATE_ID",
                                "TO_STATE_ID",
                                "ACTION_CODE"
                        }
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowTransition extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workflow_transition_seq_gen")
    @SequenceGenerator(
            name = "workflow_transition_seq_gen",
            sequenceName = "WORKFLOW_TRANSITION_SEQ",
            allocationSize = 1
    )
    @Column(name = "TRANSITION_ID")
    private Long transitionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORKFLOW_ID", nullable = false)
    private WorkflowDefinition workflow;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FROM_STATE_ID", nullable = false)
    private WorkflowStep fromState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TO_STATE_ID", nullable = false)
    private WorkflowStep toState;

    @Column(name = "ACTION_CODE", nullable = false)
    private String actionCode;

    @Column(name = "STATUS")
    private String status;
}
