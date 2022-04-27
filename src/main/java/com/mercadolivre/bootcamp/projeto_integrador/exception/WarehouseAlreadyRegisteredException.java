package com.mercadolivre.bootcamp.projeto_integrador.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class WarehouseAlreadyRegisteredException extends BaseException{
    public WarehouseAlreadyRegisteredException(Long warehouseId) {
        super("The Warehouse " + warehouseId + " is already created.", HttpStatus.CONFLICT, ZonedDateTime.now());
    }
}
