package com.alvinskylers.products.dto;

import lombok.Builder;

@Builder
public record ProductCategoryResponse(
        Long id,
        String name,
        String description
) {

}
