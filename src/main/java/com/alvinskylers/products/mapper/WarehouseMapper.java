package com.alvinskylers.products.mapper;

import com.alvinskylers.products.dto.WarehouseRequest;
import com.alvinskylers.products.dto.WarehouseResponse;
import com.alvinskylers.products.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMapper {

    public Warehouse toEntity(WarehouseRequest request) {
        return Warehouse.builder()
                .name(request.name())
                .address(request.address())
                .build();
    }

    public WarehouseResponse toResponse(Warehouse entity) {
        return WarehouseResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
