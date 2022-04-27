package com.mercadolivre.bootcamp.projeto_integrador.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SectionDTO {
    Long sectionId;
    Long warehouseId;
}
