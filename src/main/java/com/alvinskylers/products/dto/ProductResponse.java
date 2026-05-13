package com.alvinskylers.products.dto;

import com.alvinskylers.products.entity.ProductCategory;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record ProductResponse(
        String id,
        String sku,
        String slug,
        String description,
        BigDecimal price,
        BigDecimal weight,
        ProductCategory productCategory,
        LocalDateTime createdAt
) {

}
