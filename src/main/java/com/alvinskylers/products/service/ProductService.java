package com.alvinskylers.products.service;

import com.alvinskylers.products.dto.ProductRequest;
import com.alvinskylers.products.dto.ProductResponse;
import com.alvinskylers.products.entity.Product;
import com.alvinskylers.products.entity.ProductCategory;
import com.alvinskylers.products.exception.ProductNotFoundException;
import com.alvinskylers.products.mapper.ProductMapper;
import com.alvinskylers.products.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final ProductCategoryService productCategoryService;

    public ProductService(
            ProductRepository productRepository,
            ProductMapper productMapper,
            ProductCategoryService productCategoryService) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.productCategoryService = productCategoryService;
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> findByAllParams(
            String product,
            String sku,
            BigDecimal min,
            BigDecimal max,
            Pageable pageable) {
        return productRepository.findProductsByAllParams(product, sku, min, max, pageable);
    }

    public Page<Product> findByProduct(String product, Pageable pageable) {
        return productRepository.findProductByName(product, pageable);
    }

    public Page<Product> findBySKU(String sku, Pageable pageable) {
        return productRepository.findProductBySKU(sku, pageable);
    }

    public Page<Product> filterByPriceRange(BigDecimal min, BigDecimal max, Pageable pageable) {
        return productRepository.filterProductByPriceRange(min, max, pageable);
    }

    public Page<Product> filterByMinPrice(BigDecimal min, Pageable pageable) {
        return productRepository.filterProductByMinPrice(min, pageable);
    }

    public Page<Product> filterByMaxPrice(BigDecimal max, Pageable pageable) {
        return productRepository.filterProductByMaxPrice(max, pageable);
    }

    public ProductResponse createProduct(ProductRequest request)  {
        ProductCategory category = null;
        if (request.categoryId() != null) {
            category = productCategoryService.findCategoryEntity(request.categoryId());
        }

        Product product = productMapper.toEntity(request, category);
        productRepository.save(product);
        return productMapper.toResponse(product);
    }

    public ProductResponse showProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.toResponse(product);
    }

    public ProductResponse updateProduct(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        ProductCategory category = null;
        if (request.categoryId() != null) {
            category = productCategoryService.findCategoryEntity(request.categoryId());
        }

        productMapper.updateEntity(product, request, category);
        Product saved = productRepository.save(product);
        return productMapper.toResponse(saved);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(product);
    }


}
