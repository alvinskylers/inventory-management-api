package com.alvinskylers.products.controllers;

import com.alvinskylers.products.dto.WarehouseResponse;
import com.alvinskylers.products.entity.Warehouse;
import com.alvinskylers.products.mapper.WarehouseMapper;
import com.alvinskylers.products.service.WarehouseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
