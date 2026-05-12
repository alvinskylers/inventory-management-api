package com.alvinskylers.products.service;

import com.alvinskylers.products.dto.ProductCategoryRequest;
import com.alvinskylers.products.dto.ProductCategoryResponse;
import com.alvinskylers.products.entity.ProductCategory;
import com.alvinskylers.products.exception.ProductCategoryNotFoundException;
import com.alvinskylers.products.mapper.ProductCategoryMapper;
import com.alvinskylers.products.repository.ProductCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository,
                                  ProductCategoryMapper productCategoryMapper) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryMapper = productCategoryMapper;

    }

    public Page<ProductCategory> categories(String query, Pageable pageable) {
        return productCategoryRepository.findAllProductCategories(query, pageable);
    }

    public ProductCategoryResponse createCategory(ProductCategoryRequest request) {
        ProductCategory entity = productCategoryMapper.toEntity(request);
        return productCategoryMapper.toResponse(productCategoryRepository.save(entity));
    }

    public ProductCategoryResponse findCategory(BigInteger id) {
        ProductCategory entity = productCategoryRepository.findById(id)
                .orElseThrow(() -> new ProductCategoryNotFoundException(id));
        return productCategoryMapper.toResponse(entity);
    }

    public ProductCategoryResponse updateCategory(BigInteger id, ProductCategoryRequest request) {
        ProductCategory entity = productCategoryRepository.findById(id)
                .orElseThrow(() -> new ProductCategoryNotFoundException(id));
        productCategoryMapper.mapRequestToEntity(entity, request);
        return productCategoryMapper.toResponse(productCategoryRepository.save(entity));
    }

    public void deleteCategory(BigInteger id) {
        ProductCategory entity = productCategoryRepository.findById(id)
                .orElseThrow(() -> new ProductCategoryNotFoundException(id));
        productCategoryRepository.delete(entity);
    }
}
