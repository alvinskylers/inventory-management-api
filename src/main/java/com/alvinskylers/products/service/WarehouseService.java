package com.alvinskylers.products.service;

import com.alvinskylers.products.dto.WarehouseRequest;
import com.alvinskylers.products.dto.WarehouseResponse;
import com.alvinskylers.products.entity.Warehouse;
import com.alvinskylers.products.exception.WarehouseNotFoundException;
import com.alvinskylers.products.mapper.WarehouseMapper;
import com.alvinskylers.products.repository.WarehouseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;
    private final WarehouseMapper warehouseMapper;
    public WarehouseService(
            WarehouseRepository warehouseRepository,
            WarehouseMapper warehouseMapper
    ) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
    }

    public Page<Warehouse> index(Pageable pageable){
        return warehouseRepository.findAll(pageable);
    }

    public WarehouseResponse create(WarehouseRequest request) {
        Warehouse warehouse = warehouseMapper.toEntity(request);
        Warehouse saved = warehouseRepository.save(warehouse);
        return warehouseMapper.toResponse(saved);
    }

    public WarehouseResponse show(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(id));
        return warehouseMapper.toResponse(warehouse);
    }

    public WarehouseResponse update(Long id, WarehouseRequest request) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(id));
        warehouseMapper.updateEntity(warehouse, request);
        warehouseRepository.save(warehouse);
        return warehouseMapper.toResponse(warehouse);
    }

    public void delete(Long id) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new WarehouseNotFoundException(id));
        warehouseRepository.delete(warehouse);
    }

}
