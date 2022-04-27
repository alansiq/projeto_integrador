package com.mercadolivre.bootcamp.projeto_integrador.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class SectionNotFound extends BaseException{

    public SectionNotFound(Long id) {
        super("A section com ID: " + id, HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}
