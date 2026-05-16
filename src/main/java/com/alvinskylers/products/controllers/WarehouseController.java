package com.alvinskylers.products.controllers;

import com.alvinskylers.products.dto.ProductRequest;
import com.alvinskylers.products.dto.WarehouseRequest;
import com.alvinskylers.products.dto.WarehouseResponse;
import com.alvinskylers.products.entity.Product;
import com.alvinskylers.products.entity.Warehouse;
import com.alvinskylers.products.mapper.WarehouseMapper;
import com.alvinskylers.products.service.WarehouseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;
    private final WarehouseMapper warehouseMapper;

    public WarehouseController(
            WarehouseService warehouseService,
            WarehouseMapper warehouseMapper) {
        this.warehouseService = warehouseService;
        this.warehouseMapper = warehouseMapper;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> index(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        Page<Warehouse> warehousePage = warehouseService.index(pageable);

        List<WarehouseResponse> warehouses = warehousePage
                .stream()
                .map(warehouseMapper::toResponse)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("data", warehouses);
        response.put("currentPage", warehousePage.getNumber());
        response.put("totalItems", warehousePage.getTotalElements());
        response.put("totalPages", warehousePage.getTotalPages());
        response.put("hasNext", warehousePage.hasNext());
        response.put("hasPrevious", warehousePage.hasPrevious());

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<WarehouseResponse> create(@Valid @RequestBody WarehouseRequest request) {
        WarehouseResponse response = warehouseService.create(request);
        URI location = URI.create("/api/v1/warehouse/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseResponse> show(@PathVariable Long id) {
        WarehouseResponse response = warehouseService.show(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody WarehouseRequest request
    ) {
        WarehouseResponse response = warehouseService.update(id, request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        warehouseService.delete(id);
        return ResponseEntity.ok().build();
    }
}
