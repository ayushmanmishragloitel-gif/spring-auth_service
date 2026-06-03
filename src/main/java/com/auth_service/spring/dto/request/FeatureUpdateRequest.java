package com.auth_service.spring.dto.request;

import com.auth_service.spring.enums.FeatureType;
import com.auth_service.spring.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request DTO for updating feature")
public class FeatureUpdateRequest {

    @Schema(
            description = "Unique feature code",
            example = "USER_MANAGEMENT"
    )
    private String featureCode;

    @Schema(
            description = "Feature display name",
            example = "User Management"
    )
    private String featureName;

    @Schema(
            description = "Feature type",
            example = "MODULE"
    )
    private FeatureType featureType;

    @Schema(
            description = "Parent feature id. Required for SUBMODULE, PAGE and ACTION",
            example = "1",
            nullable = true
    )
    private Long parentFeatureId;

    @Schema(
            description = "Frontend route path",
            example = "/user-management"
    )
    private String slug;

    @Schema(
            description = "Frontend icon name. Allowed only for MODULE and SUBMODULE",
            example = "users"
    )
    private String icon;

    @Schema(
            description = "Display order for frontend sorting",
            example = "10"
    )
    private Integer displayOrder;

    @Schema(
            description = "Feature status",
            example = "ACTIVE"
    )
    private Status status;
}