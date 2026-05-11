package com.alvinskylers.products.dto;

import lombok.Builder;

import java.math.BigInteger;

@Builder
public record ProductCategoryResponse(
        BigInteger id,
        String name,
        String description
) {

}
