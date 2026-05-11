package com.alvinskylers.products.service;

import com.alvinskylers.products.entity.ProductCategory;
import com.alvinskylers.products.repository.ProductCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public Page<ProductCategory> categories(String query, Pageable pageable) {
        return productCategoryRepository.findAllProductCategories(query, pageable);
    }
}
