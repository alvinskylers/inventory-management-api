package com.alvinskylers.products.exception;

public class WarehouseNotFoundException extends ResourceNotFoundException {
    public WarehouseNotFoundException(Long id) {
        super("Warehouse with id: it st" + id + " was not found.");
    }
}
