package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.exception.BatchStockIdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.InvalidProductException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.BatchStockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
@AllArgsConstructor
@Service
public class BatchStockServiceImpl implements BatchStockService{

    private final BatchStockRepository repository;

    @Override
    public BigDecimal calculateTotalVolume(BatchStock batchStock) {
        Integer productQuantity = batchStock.getCurrentQuantity();
        BigDecimal volumePerProduct = batchStock.getProduct().getVolume();
        // Multiply currentQuantity per volumePerProduct to calculate batch total volume
        return volumePerProduct.multiply(BigDecimal.valueOf(productQuantity));
    }

    @Override
    public BatchStock create(BatchStock batchStock) {
        // Todo: Verify if Product exists in Entity Product
        return repository.save(batchStock);
    }

    @Override
    public List<BatchStock> listAll() {
        List<BatchStock> list = repository.findAll();
        if(list.isEmpty()) throw new EmptyListException();
        return list;
    }

    @Override
    public BatchStock findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new BatchStockIdNotFoundException(id));
    }

    @Override
    public BatchStock update(BatchStock batchStock) {
        BatchStock updatedBatchStock = findById(batchStock.getBatchNumber());
        updatedBatchStock.setCurrentQuantity(batchStock.getCurrentQuantity());
        updatedBatchStock.setPrice(batchStock.getPrice());
        updatedBatchStock.setDueDate(batchStock.getDueDate());
        updatedBatchStock.setManufacturingDate(batchStock.getManufacturingDate());
        updatedBatchStock.setManufacturingTime(batchStock.getManufacturingTime());
        return repository.save(batchStock);
    }

    @Override
    public List<BatchStock> findAllByProductId(String id) {
        List<BatchStock> batchStockList = repository.findAllByProduct_Id(id);
        if(batchStockList.isEmpty()) throw new InvalidProductException(id);
        return batchStockList;
    }

    @Override
    public void remove(Long id) {
        BatchStock batchStock = findById(id);
        repository.delete(batchStock);
    }
}
