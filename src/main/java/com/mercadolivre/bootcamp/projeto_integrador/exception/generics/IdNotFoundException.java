package com.mercadolivre.bootcamp.projeto_integrador.exception.generics;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class IdNotFoundException extends BaseException {

    public IdNotFoundException(Long id) {
        super("O Id: " + id + " não foi encontrado.", HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}
