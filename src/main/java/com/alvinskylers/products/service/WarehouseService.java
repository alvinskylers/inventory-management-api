package com.alvinskylers.products.service;

import com.alvinskylers.products.entity.Warehouse;
import com.alvinskylers.products.repository.WarehouseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Page<Warehouse> index(Pageable pageable){
        return warehouseRepository.findAll(pageable);
    }

}
