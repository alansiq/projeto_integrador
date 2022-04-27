package com.mercadolivre.bootcamp.projeto_integrador.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class RepresentativeNotFoundException extends BaseException{
    public RepresentativeNotFoundException(Long id) {
        super("O representante com o ID " + id + " não foi encontrado.", HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}
