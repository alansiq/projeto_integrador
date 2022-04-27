package com.mercadolivre.bootcamp.projeto_integrador.dto;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class NewProductDto {
    private String productName;
    private BigDecimal volume;
    private Float minimumTemperature;
    private Float maxTemperature;
    private Category category;
    private String salesman_id;
}
