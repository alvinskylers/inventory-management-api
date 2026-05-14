package com.alvinskylers.products.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;

public record ProductRequest(

        @NotBlank(message = "Name is required")
        @Size(min = 6, max = 100, message = "Name must be between 6 and 100 characters")
        String name,

        @NotBlank(message = "Description is required")
        @Size(max = 500, message = "Description must be under 500 characters")
        String description,

        @NotNull(message = "Price is required")
        @DecimalMin(value = "0.01", message = "Price must be at least 0.01")
        @Digits(integer = 5, fraction = 2, message = "Invalid price format")
        BigDecimal price,

        @NotNull(message = "Weight is required")
        @DecimalMin(value = "0.01", message = "Weight must be at least 0.01")
        BigDecimal weight,

        @NotNull(message = "You must select a category")
        @Positive(message = "Invalid category reference")
        Long categoryId
) {}
