package com.mercadolivre.bootcamp.projeto_integrador.service;


import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.warehouse.EmptyListWarehouseCityException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WarehouseServiceImpl implements WarehouseService{

    private final WarehouseRepository warehouseRepository;

    @Override
    public Warehouse findById(Long warehouseId) {
        Optional<Warehouse> wh = warehouseRepository.findById(warehouseId);
        if(wh.isEmpty()){
            throw new IdNotFoundException(warehouseId);
        }else{
            return wh.get();
        }
    }

    @Override
    public List<Warehouse> findAll() {
        List<Warehouse> warehouseList = warehouseRepository.findAll();
        if(warehouseList.isEmpty()) throw new EmptyListException();
        return warehouseList;
    }

    @Override
    public boolean isValidWarehouse(Long warehouseId) {
        Optional<Warehouse> wh = warehouseRepository.findById(warehouseId);
        if(wh.isEmpty()){
            throw new IdNotFoundException(warehouseId);
        }else{
            return true;
        }
    }

    @Override
    public Warehouse save(Warehouse wh) {
        return warehouseRepository.save(wh);
    }

    @Override
    public void delete(Long warehouseId) {
        Optional<Warehouse> wh = warehouseRepository.findById(warehouseId);
        warehouseRepository.delete(wh.get());
    }

    @Override
    public List<Warehouse> findAllByCity(String city) {
        List<Warehouse> warehouses = warehouseRepository.getAllByAddressCity(city);
        if(warehouses.isEmpty()) throw new EmptyListWarehouseCityException(city);
        return warehouses;
    }


}
