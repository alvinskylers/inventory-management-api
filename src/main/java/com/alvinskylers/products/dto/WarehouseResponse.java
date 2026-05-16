package com.alvinskylers.products.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record WarehouseResponse(
        Long id,
        String name,
        String address,
        LocalDateTime createdAt
) {
}
