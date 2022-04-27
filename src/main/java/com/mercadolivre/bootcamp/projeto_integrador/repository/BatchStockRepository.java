package com.mercadolivre.bootcamp.projeto_integrador.repository;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatchStockRepository extends JpaRepository<BatchStock, Long> {

    List<BatchStock> findAllByProduct_Id(Long productId);
}
