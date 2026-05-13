package com.alvinskylers.products.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record ProductRequest(

        @Size(min=6 ,max=100, message = "name should be between 6 to 100 characters")
        @NotBlank
        String name,

        @NotBlank
        @Size(max=500, message = "description should be under 500 characters")
        String description,

        @NotNull
        @DecimalMin(value="0.01", message="price cannot be under 0")
        @Digits(integer=5, fraction=2, message = "invalid price format")
        double price,

        @Positive(message = "weight cannot be negative")
        double weight,

        @NotNull(message = "You must select a category")
        @Positive(message = "Invalid category reference")
        Long categoryId
) {
}
