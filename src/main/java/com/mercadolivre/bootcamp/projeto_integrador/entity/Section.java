package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionId;
    private BigDecimal capacity;
    private float currentTemperature;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<InBoundOrder> listInBoundOrder;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Long warehouseId;

}
