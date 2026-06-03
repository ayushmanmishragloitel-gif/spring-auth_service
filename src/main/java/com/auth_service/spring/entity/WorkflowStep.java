package com.auth_service.spring.entity;

import jakarta.persistence.*;
import lombok.*;
import com.auth_service.spring.enums.Status;

@Entity
@Table(name = "WORKFLOW_STEP")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowStep extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workflow_step_seq_gen")
    @SequenceGenerator(
            name = "workflow_step_seq_gen",
            sequenceName = "WORKFLOW_STEP_SEQ",
            allocationSize = 1
    )
    @Column(name = "STEP_ID")
    private Long stepId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WORKFLOW_ID", nullable = false)
    private WorkflowDefinition workflow;

    @Column(name = "STEP_CODE", nullable = false)
    private String stepCode;

    @Column(name = "STEP_NAME", nullable = false)
    private String stepName;

    @Column(name = "STEP_ORDER", nullable = false)
    private Integer stepOrder;

    @Column(name = "IS_START_STEP")
    private Boolean isStartStep;

    @Column(name = "IS_END_STEP")
    private Boolean isEndStep;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private Status status;
}
