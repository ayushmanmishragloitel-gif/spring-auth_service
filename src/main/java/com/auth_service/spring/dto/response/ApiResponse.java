package com.auth_service.spring.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private boolean success;
    private int status;
    private String message;
    private T payload;
    private List<String> details;

    public static <T> ApiResponse<T> success(String message, T payload) {
        return ApiResponse.<T>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .message(message)
                .payload(payload)
                .build();
    }

    public static <T> ApiResponse<T> created(String message, T payload) {
        return ApiResponse.<T>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .message(message)
                .payload(payload)
                .build();
    }

    public static <T> ApiResponse<T> error(int status, String message, List<String> details) {
        return ApiResponse.<T>builder()
                .success(false)
                .status(status)
                .message(message)
                .details(details)
                .build();
    }

    public static <T> ApiResponse<T> error(int status, String message) {
        return ApiResponse.<T>builder()
                .success(false)
                .status(status)
                .message(message)
                .build();
    }
}
