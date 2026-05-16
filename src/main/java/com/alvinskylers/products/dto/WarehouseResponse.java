package com.alvinskylers.products.dto;

import java.time.LocalDateTime;

public record WarehouseResponse(
        Long id,
        String name,
        String address,
        LocalDateTime createdAt
) {
}
