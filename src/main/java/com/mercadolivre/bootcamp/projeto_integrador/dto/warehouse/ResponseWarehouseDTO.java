package com.mercadolivre.bootcamp.projeto_integrador.dto.warehouse;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ResponseWarehouseDTO {

    private Long productId;
    private List<Warehouse> listWarehouse;

    public static ResponseWarehouseDTO convert(Long productId, List<Warehouse> listWarehouse) {
        return ResponseWarehouseDTO.builder()
                .productId(productId)
                .listWarehouse(listWarehouse)
                .build();
    }


}
