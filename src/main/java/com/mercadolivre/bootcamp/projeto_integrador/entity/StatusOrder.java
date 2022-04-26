package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusOrder {

    CART("Carrinho"),
    PROCESSING("Processando"),
    CLOSED("Fechado");

    private String value;

}
