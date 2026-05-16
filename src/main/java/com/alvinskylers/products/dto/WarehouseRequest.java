package com.alvinskylers.products.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record WarehouseRequest(

        @NotNull
        @NotBlank
        @Size(min=8, max=30, message = "message should be between 8 to 30 characters")
        String name,

        @NotNull
        @NotBlank
        @Size(min=5, max=100, message = "address should be between 5 to 100 characters")
        String address
) {
}
