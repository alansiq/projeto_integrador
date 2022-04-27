package com.mercadolivre.bootcamp.projeto_integrador.dto;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewSectionDTO {

    private BigDecimal capacity;
    private float currentTemperature;
    private Category category;
    private Long warehouseId;

    public static Section convert(NewSectionDTO newSectionDTO){
        return Section.builder()
                .capacity(newSectionDTO.getCapacity())
                .currentTemperature(newSectionDTO.getCurrentTemperature())
                .category(newSectionDTO.getCategory())
                .warehouseId(newSectionDTO.getWarehouseId()).build();
    }
}
