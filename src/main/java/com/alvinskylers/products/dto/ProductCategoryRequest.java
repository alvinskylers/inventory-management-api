package com.alvinskylers.products.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record ProductCategoryRequest(

        Long id,

        @NotBlank(message = "Name is required")
        @Size(min=3, max=20, message="name is 3 to 20 characters")
        String name,

        @Size(min=5, message = "description is less than 5 characters")
        String description
) {

}
