package com.mercadolivre.bootcamp.projeto_integrador.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class WarehouseDoesntExistException extends BaseException{
    public WarehouseDoesntExistException(Long warehouseId) {
        super("Warehouse with id" + warehouseId + " does not exist.", HttpStatus.CONFLICT, ZonedDateTime.now());
    }
}
