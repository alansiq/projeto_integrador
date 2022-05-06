package com.mercadolivre.bootcamp.projeto_integrador.controller;


import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("api/v1/fresh-products/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;



    @PostMapping()
    public ResponseEntity createWarehouse(@RequestBody String name) {
        Warehouse wh = new Warehouse(name);
        Warehouse finalWh = warehouseService.save(wh);
        return new ResponseEntity(finalWh, HttpStatus.CREATED);
    }

    @DeleteMapping("/{warehouseId}")
    public ResponseEntity deleteWarehouse(@PathVariable Long warehouseId) {
        try{
            warehouseService.delete(warehouseId);
            return ResponseEntity.ok().body("Deleted with Success");
        }catch(IdNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping()
    public ResponseEntity getWarehouse(@RequestParam Long warehouseId) {
        Warehouse wh = warehouseService.findById(warehouseId);
        return ResponseEntity.ok().body(wh);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Warehouse>> getAllWarehouseByCity(@RequestParam String city){
        List<Warehouse> warehouseList = warehouseService.findAllByCity(city);
        return  ResponseEntity.status(HttpStatus.OK).body(warehouseList);
    }
}
