package com.mercadolivre.bootcamp.projeto_integrador.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class SalesmanDoesNotExistException extends BaseException {
    public SalesmanDoesNotExistException(Long salesmanId) {
        super("Não foi possível encontrar um vendedor com o id: " + salesmanId, HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}
