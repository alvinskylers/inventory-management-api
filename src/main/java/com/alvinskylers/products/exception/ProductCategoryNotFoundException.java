package com.alvinskylers.products.exception;

import java.math.BigInteger;

public class ProductCategoryNotFoundException extends ResourceNotFoundException {
    public ProductCategoryNotFoundException(Long id) {
        super("product category with id: " + id + " was not found.");
    }
}
