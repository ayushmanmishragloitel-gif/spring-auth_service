package com.auth_service.spring.dto.request;

import com.auth_service.spring.enums.FeatureType;
import com.auth_service.spring.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Request DTO for creating feature")
public class FeatureRequest {

    @NotBlank(message = "Feature code is required")
    @Schema(
            description = "Unique feature code",
            example = "USER_MANAGEMENT"
    )
    private String featureCode;

    @NotBlank(message = "Feature name is required")
    @Schema(
            description = "Feature display name",
            example = "User Management"
    )
    private String featureName;

    @NotNull(message = "Feature type is required")
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

    @NotNull(message = "Display order is required")
    @Schema(
            description = "Display order for frontend sorting",
            example = "10"
    )
    private Integer displayOrder;

    @NotNull(message = "Status is required")
    @Schema(
            description = "Feature status",
            example = "ACTIVE"
    )
    private Status status;
}