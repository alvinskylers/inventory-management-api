package com.alvinskylers.products.dto;

import com.alvinskylers.products.entity.ProductCategory;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProductResponse(
        String id,
        String sku,
        String slug,
        String description,
        double price,
        ProductCategory productCategory,
        LocalDateTime createdAt
) {

}
