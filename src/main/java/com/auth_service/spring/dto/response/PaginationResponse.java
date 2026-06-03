package com.auth_service.spring.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaginationResponse<T> {
    private boolean success;
    private int status;
    private String message;
    private PaginationInfo pagination;
    private T payload;
    private List<String> details;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class PaginationInfo {
        private int page;
        private int limit;
        private long totalElements;
        private int totalPages;
        private boolean last;
    }

    public static <T> PaginationResponse<T> success(String message, T payload, PaginationInfo pagination) {
        return PaginationResponse.<T>builder()
                .success(true)
                .status(200)
                .message(message)
                .payload(payload)
                .pagination(pagination)
                .build();
    }

    public static <T> PaginationResponse<T> error(int status, String message, List<String> details) {
        return PaginationResponse.<T>builder()
                .success(false)
                .status(status)
                .message(message)
                .details(details)
                .build();
    }

    public static <T> PaginationResponse<T> error(int status, String message) {
        return PaginationResponse.<T>builder()
                .success(false)
                .status(status)
                .message(message)
                .build();
    }
}

