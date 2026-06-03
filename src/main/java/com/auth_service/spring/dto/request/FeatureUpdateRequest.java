package com.auth_service.spring.dto.request;

import com.auth_service.spring.enums.FeatureType;
import com.auth_service.spring.enums.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FeatureUpdateRequest {

    private String featureCode;

    private String featureName;

    private FeatureType featureType;

    private Long parentFeatureId;

    private String slug;

    private String icon;

    private Integer displayOrder;

    private Status status;
}
