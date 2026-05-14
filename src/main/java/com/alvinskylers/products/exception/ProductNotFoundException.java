package com.alvinskylers.products.exception;

public class ProductNotFoundException extends ResourceNotFoundException {
    public ProductNotFoundException(Long id) {
        super("Product with id " + id  + " was not found.");
    }
}
