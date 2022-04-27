package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;

import java.math.BigDecimal;
import java.util.List;

public interface BatchStockService {
    BigDecimal calculateTotalVolume(BatchStock batchStock);
    BatchStock create(BatchStock batchStock);
    List<BatchStock> listAll();
    BatchStock findById(Long id);
    BatchStock update(BatchStock batchStock);
    List<BatchStock> findAllByProductId(String id);
    void remove(Long id);
}
