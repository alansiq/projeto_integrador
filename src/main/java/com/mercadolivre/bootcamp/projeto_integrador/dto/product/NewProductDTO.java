package com.mercadolivre.bootcamp.projeto_integrador.dto.product;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class NewProductDTO {
    private String productName;
    private BigDecimal volume;
    private Float minimumTemperature;
    private Float maxTemperature;
    private Category category;
    private Long salesman_id;
}
