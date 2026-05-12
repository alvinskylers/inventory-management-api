package com.alvinskylers.products.mapper;

import com.alvinskylers.products.dto.ProductCategoryRequest;
import com.alvinskylers.products.dto.ProductCategoryResponse;
import com.alvinskylers.products.entity.ProductCategory;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryMapper {

    public ProductCategory toEntity(ProductCategoryRequest request) {
        return ProductCategory.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .build();
    }

    public ProductCategoryResponse toResponse(ProductCategory entity) {
        return ProductCategoryResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .build();
    }

    public void mapRequestToEntity(ProductCategory productCategory, ProductCategoryRequest request) {
        productCategory.setName(request.name());
        productCategory.setDescription(request.description());
    }
}
