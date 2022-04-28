package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.BatchStockDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.NewBatchStockDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.UpdateBatchStockDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrder;
import com.mercadolivre.bootcamp.projeto_integrador.service.BatchStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(BatchStockController.baseUri)
public class BatchStockController {

    public static final String baseUri = "/api/v1/fresh-products/batchstock";

    @Autowired
    private BatchStockService service;

    @PostMapping
    public ResponseEntity<NewBatchStockDTO> newBatchStock(@RequestBody NewBatchStockDTO batchStockDTO){
        BatchStock batchStock = batchStockDTO.map();
        service.create(batchStock);

        NewBatchStockDTO d = NewBatchStockDTO.map(batchStock);
        return new ResponseEntity(d, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UpdateBatchStockDTO> updateBatchStock(@RequestBody UpdateBatchStockDTO updateBatchStockDTO){
        BatchStock batchStock = updateBatchStockDTO.map();
        BatchStock updatedBatchStock = service.update(batchStock);
        return ResponseEntity.ok(UpdateBatchStockDTO.map(updatedBatchStock));
    }

    @GetMapping
    public ResponseEntity<List<BatchStockDTO>> listAllBatchStocks(){
        List<BatchStock> batchStockList = service.list();
        List<BatchStockDTO> result = batchStockList.stream().map(BatchStockDTO::map).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

    @GetMapping("{batchNumber}")
    public ResponseEntity<BatchStockDTO> getBatchStockById(@RequestParam(value = "batchNumber") Long id){
        BatchStock batchStock = service.findById(id);
        BatchStockDTO result = BatchStockDTO.map(batchStock);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{batchStockNumber}")
    public ResponseEntity<Long> deleteBatchStock(@PathVariable(value = "batchStockNumber") Long id){
        service.remove(id);
        return ResponseEntity.ok(id);
    }
}
