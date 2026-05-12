package com.alvinskylers.products.exception;

import java.math.BigInteger;

public class ProductCategoryException extends ResourceNotFoundException {
    public ProductCategoryException(BigInteger id) {
        super("product category with id: " + id + " was not found.");
    }
}
