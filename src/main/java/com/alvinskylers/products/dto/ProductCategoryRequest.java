package com.alvinskylers.products.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.math.BigInteger;

@Builder
public record ProductCategoryRequest(

        BigInteger id,

        @NotNull
        @Size(min=3, max=5)
        String name,

        @Size(min=5)
        String description
) {

}
