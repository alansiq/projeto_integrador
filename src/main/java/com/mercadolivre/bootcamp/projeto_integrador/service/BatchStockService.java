package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;

import java.math.BigDecimal;
import java.util.List;

public interface BatchStockService {
    BatchStock create(BatchStock batchStock);
    List<BatchStock> findAll();
    BatchStock findById(Long id);
    List<BatchStock> findAllByProductId(String id);
    BatchStock update(BatchStock batchStock);
    void remove(Long id);
    BigDecimal calculateTotalVolume(BatchStock batchStock);
}
