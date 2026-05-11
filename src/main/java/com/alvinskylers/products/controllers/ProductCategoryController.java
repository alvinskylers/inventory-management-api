package com.alvinskylers.products.controllers;

import com.alvinskylers.products.dto.ProductCategoryRequest;
import com.alvinskylers.products.dto.ProductCategoryResponse;
import com.alvinskylers.products.entity.ProductCategory;
import com.alvinskylers.products.mapper.ProductCategoryMapper;
import com.alvinskylers.products.service.ProductCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/product-categories")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;
    private final ProductCategoryMapper productCategoryMapper;

    public ProductCategoryController(ProductCategoryService productCategoryService, ProductCategoryMapper productCategoryMapper) {
        this.productCategoryService = productCategoryService;
        this.productCategoryMapper = productCategoryMapper;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> categories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String keyword
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductCategory> productCategoryPage =
                productCategoryService.categories(keyword, pageable);

        List<ProductCategoryResponse>  data = productCategoryPage.getContent()
                .stream()
                .map(productCategoryMapper::toResponse)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("currentPage", productCategoryPage.getNumber());
        response.put("totalItems", productCategoryPage.getTotalElements());
        response.put("totalPages", productCategoryPage.getTotalPages());
        response.put("next", productCategoryPage.hasNext());
        response.put("previous", productCategoryPage.hasPrevious());
        response.put("time", LocalDateTime.now());

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ProductCategoryResponse> create(@RequestBody ProductCategoryRequest request) {
        ProductCategoryResponse response = productCategoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
