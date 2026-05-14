package com.alvinskylers.products.mapper;

import com.alvinskylers.products.dto.ProductCategoryResponse;
import com.alvinskylers.products.dto.ProductRequest;
import com.alvinskylers.products.dto.ProductResponse;
import com.alvinskylers.products.entity.Product;
import com.alvinskylers.products.entity.ProductCategory;
import com.alvinskylers.products.service.ProductCategoryService;
import com.alvinskylers.products.util.SlugAndSkuGenerator;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private final SlugAndSkuGenerator generator;
    private final ProductCategoryService productCategoryService;

    public ProductMapper (
            SlugAndSkuGenerator generator,
            ProductCategoryService productCategoryService
    ) {
        this.generator = generator;
        this.productCategoryService = productCategoryService;
    }

    public Product toEntity(ProductRequest request) {

        ProductCategory productCategory = null;

        if (request.categoryId() != null) {
            productCategory = productCategoryService.findCategoryEntity(request.categoryId());
        }

        return Product.builder()
                .name(request.name())
                .sku(generator.generateSKU(request.name()))
                .slug(generator.generateSlug(request.name()))
                .description(request.description())
                .price(request.price())
                .weight(request.weight())
                .productCategory(productCategory)
                .build();
    }

    public ProductResponse toResponse(Product entity) {
        ProductCategory productCategory = null;

        if (entity.getProductCategory() != null) {
            productCategory = productCategoryService.findCategoryEntity(entity.getProductCategory().getId());
        }

        assert productCategory != null;
        ProductCategoryResponse response = ProductCategoryResponse.builder()
                .id(productCategory.getId())
                .name(productCategory.getName())
                .description(productCategory.getDescription())
                .build();

        return ProductResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .sku(entity.getSku())
                .slug(entity.getSlug())
                .price(entity.getPrice())
                .weight(entity.getWeight())
                .productCategory(response)
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
