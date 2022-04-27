package com.mercadolivre.bootcamp.projeto_integrador.exception.generics;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class EmptyListException extends BaseException {

    public EmptyListException() {
        super("Não foi encontrato nenhum registro", HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}
