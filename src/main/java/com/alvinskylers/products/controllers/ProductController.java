package com.alvinskylers.products.controllers;

import com.alvinskylers.products.dto.ProductRequest;
import com.alvinskylers.products.dto.ProductResponse;
import com.alvinskylers.products.entity.Product;
import com.alvinskylers.products.mapper.ProductMapper;
import com.alvinskylers.products.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> products(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) String sku,
            @RequestParam(required = false) BigDecimal min,
            @RequestParam(required = false) BigDecimal max
            ) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Product> productPage;

        if (product != null &&
                sku != null &&
                min != null &&
                max != null) {
            productPage = productService.findByAllParams(product, sku, min, max, pageable);
        } else if (min != null && max != null) {
            productPage = productService.filterByPriceRange(min, max, pageable);
        } else if (product != null) {
            productPage = productService.findByProduct(product, pageable);
        } else if (sku != null ) {
            productPage = productService.findBySKU(sku, pageable);
        } else if (min != null) {
            productPage = productService.filterByMinPrice(min, pageable);
        } else if (max != null){
            productPage = productService.filterByMaxPrice(max, pageable);
        } else  {
            productPage = productService.findAll(pageable);
        }

        List<ProductResponse> products = productPage
                .stream()
                .map(productMapper::toResponse)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("data", products);
        response.put("currentPage", productPage.getNumber());
        response.put("totalItems", productPage.getTotalElements());
        response.put("totalPages", productPage.getTotalPages());
        response.put("hasNext", productPage.hasNext());
        response.put("hasPrevious", productPage.hasPrevious());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        ProductResponse response = productService.createProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> show(@PathVariable Long id) {
        ProductResponse response = productService.showProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {
        ProductResponse response = productService.updateProduct(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

}
