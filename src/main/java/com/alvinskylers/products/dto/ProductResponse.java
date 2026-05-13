package com.alvinskylers.products.dto;

import com.alvinskylers.products.entity.ProductCategory;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record ProductResponse(
        Long id,
        String name,
        String sku,
        String slug,
        String description,
        BigDecimal price,
        BigDecimal weight,
        ProductCategoryResponse productCategory,
        LocalDateTime createdAt
) {

}
