package com.mercadolivre.bootcamp.projeto_integrador.exception.PurchaseOrderItensExceptions;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class PurchaseOrderItensNotFoundException extends BaseException {
    public PurchaseOrderItensNotFoundException(String PurchaseOrderItensId) {
        super("O id" + PurchaseOrderItensId + " não foi encontrado.", HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}
