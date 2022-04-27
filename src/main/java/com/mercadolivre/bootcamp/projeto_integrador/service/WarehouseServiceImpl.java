package com.mercadolivre.bootcamp.projeto_integrador.service;


import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;
import com.mercadolivre.bootcamp.projeto_integrador.exception.NoWarehouseCreatedException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.WarehouseDoesntExistException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class WarehouseServiceImpl implements WarehouseService{
    private WarehouseRepository warehouseRepository;

    @Override
    public Warehouse findById(Long warehouseId) {
        Optional<Warehouse> wh = warehouseRepository.findById(warehouseId);
        if(wh.isEmpty()){
            throw new WarehouseDoesntExistException(warehouseId);
        }else{
            return wh.get();
        }
    }

    @Override
    public List<Warehouse> findAll() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        if(warehouses.isEmpty()){
            throw new NoWarehouseCreatedException();
        }
        return warehouses;
    }

    @Override
    public boolean isValidWarehouse(Long warehouseId) {
        Optional<Warehouse> wh = warehouseRepository.findById(warehouseId);
        if(wh.isEmpty()){
            throw new WarehouseDoesntExistException(warehouseId);
        }else{
            return true;
        }
    }

    @Override
    public Warehouse save(Warehouse wh) {
        return warehouseRepository.save(wh);
    }


}
