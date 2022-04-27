package com.mercadolivre.bootcamp.projeto_integrador.dto;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class ProductDto {
    private Long id;
    private String productName;
    private BigDecimal volume;
    private Float minimumTemperature;
    private Float maxTemperature;
    private Category category;
    private Long salesman_id;

    public static ProductDto convert(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .productName(product.getProductName())
                .volume(product.getVolume())
                .minimumTemperature(product.getMinimumTemperature())
                .maxTemperature(product.getMaxTemperature())
                .category(product.getCategory())
                .salesman_id(product.getSalesman().getId())
                .build();
    }

    public static List<ProductDto> convert(List<Product> productList) {
        return productList.stream().map(ProductDto::convert).collect(Collectors.toList());
    }
}
