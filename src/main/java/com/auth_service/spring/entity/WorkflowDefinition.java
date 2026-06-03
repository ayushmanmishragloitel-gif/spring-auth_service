package com.auth_service.spring.entity;

import jakarta.persistence.*;
import lombok.*;
import com.auth_service.spring.enums.Status;

@Entity
@Table(
        name = "WORKFLOW_DEFINITION",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_WORKFLOW_TENANT_MODULE",
                        columnNames = {"TENANT_ID", "MODULE_CODE"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkflowDefinition extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workflow_definition_seq_gen")
    @SequenceGenerator(
            name = "workflow_definition_seq_gen",
            sequenceName = "WORKFLOW_DEFINITION_SEQ",
            allocationSize = 1
    )
    @Column(name = "WORKFLOW_ID")
    private Long workflowId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TENANT_ID", nullable = false)
    private MasTenancy tenant;

    @Column(name = "MODULE_CODE", nullable = false, length = 100)
    private String moduleCode;

    @Column(name = "WORKFLOW_NAME", nullable = false, length = 200)
    private String workflowName;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 20)
    private Status status;
}