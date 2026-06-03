package com.auth_service.spring.entity;


import com.auth_service.spring.enums.FeatureType;
import com.auth_service.spring.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(
        name = "FEATURES",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_FEATURE_CODE",
                        columnNames = "FEATURE_CODE"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE FEATURES SET IS_DELETED = true WHERE FEATURE_ID = ?")
@SQLRestriction("IS_DELETED = false")
public class Feature extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "features_seq_gen")
    @SequenceGenerator(
            name = "features_seq_gen",
            sequenceName = "FEATURES_SEQ",
            allocationSize = 1
    )
    @Column(name = "FEATURE_ID")
    private Long featureId;

    @Column(name = "FEATURE_CODE", nullable = false, length = 100)
    private String featureCode;

    @Column(name = "FEATURE_NAME", nullable = false, length = 200)
    private String featureName;

    @Enumerated(EnumType.STRING)
    @Column(name = "FEATURE_TYPE", nullable = false)
    private FeatureType featureType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_FEATURE_ID")
    private Feature parentFeature;

    @Column(name = "SLUG", length = 200)
    private String slug;

    @Column(name = "ICON", length = 100)
    private String icon;

    @Column(name = "DISPLAY_ORDER")
    private Integer displayOrder;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", length = 20)
    private Status status;
}