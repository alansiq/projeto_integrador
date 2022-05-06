package com.mercadolivre.bootcamp.projeto_integrador.exception.warehouse;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class EmptyListWarehouseCityException extends BaseException {

    public EmptyListWarehouseCityException(String city) {
        super("Nenhum armazem encontrado em: " + city, HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}
