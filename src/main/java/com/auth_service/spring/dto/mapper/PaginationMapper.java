package com.auth_service.spring.dto.mapper;


import com.auth_service.spring.dto.response.PaginationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PaginationMapper {
    public static <T> PaginationResponse.PaginationInfo map(Page<T> page) {

        return PaginationResponse.PaginationInfo.builder()
                .page(page.getNumber() + 1)
                .limit(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }
    public static PaginationResponse.PaginationInfo mapEmpty(Pageable pageable) {

        return PaginationResponse.PaginationInfo.builder()
                .page(pageable.getPageNumber())
                .limit(pageable.getPageSize())
                .totalElements(0)
                .totalPages(0)
                .last(true)
                .build();
    }
}

