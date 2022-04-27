package com.mercadolivre.bootcamp.projeto_integrador.service;


import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
    Warehouse findById(Long warehouseId);
    List<Warehouse> findAll();
    boolean isValidWarehouse(Long warehouseId);
    Warehouse save(Warehouse wh);
}
