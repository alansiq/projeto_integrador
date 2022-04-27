package com.mercadolivre.bootcamp.projeto_integrador.factory;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewInBoundOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.SectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import com.mercadolivre.bootcamp.projeto_integrador.exception.inBoundOrderException.InvalidInboundOrderException;
import com.mercadolivre.bootcamp.projeto_integrador.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

@Component
@AllArgsConstructor
public class InBoundOrderFactory {
    private final InBoundOrderService inBoundOrderService;
    private final SectionService sectionService;
    private final WarehouseService warehouseService;
    private final RepresentativeService representativeService;
    private final BatchStockService batchStockService;

    public InBoundOrder createInBoundOrder(NewInBoundOrderDTO newInBoundOrderDTO) {
        SectionDTO inboundOrderSection = newInBoundOrderDTO.getSectionDTO();
        boolean isInboundOrderValid = this.isInboundOrderValid(newInBoundOrderDTO);
        boolean isBatchStockValid = this.isBatchStockValid(newInBoundOrderDTO.getBatchStock(), inboundOrderSection.getSectionId());

        if(!isInboundOrderValid || !isBatchStockValid) {
            // TODO: 27/04/22 MAKE A BETTER EXCEPTION HANDLING FOR EACH VALIDATION
            throw new InvalidInboundOrderException();
        }

        return inBoundOrderService.addInBoundOrder(newInBoundOrderDTO);
    }

    private boolean isInboundOrderValid(NewInBoundOrderDTO newInBoundOrderDTO) {
        /* Faz validação de sectionId, warehouseId e representativeId em seus respectivos
           services. Caso um deles não seja valido, a inboundOrder é inválida e o método retorna false.
         */

        Long sectionId = newInBoundOrderDTO.getSectionDTO().getSectionId();
        Long warehouseId = newInBoundOrderDTO.getSectionDTO().getWarehouseId();
        Long representativeId = newInBoundOrderDTO.getRepresentativeId();

        boolean isSectionValid = sectionService.isSectionValid(sectionId);
        boolean isWarehouseValid = warehouseService.isValidWarehouse(warehouseId);
        boolean isRepresentativeAssociatedWithSection = representativeService
                .isRepresentativeAssociatedWithSection(representativeId, sectionId);

        return isSectionValid && isWarehouseValid && isRepresentativeAssociatedWithSection;
    }

    private boolean isBatchStockValid(BatchStock batchStock, Long sectionId) {
        Category productCategory = batchStock.getProduct().getCategory();
        BigDecimal totalVolume = batchStockService.calculateTotalVolume(batchStock);

        boolean isSectionAppropriateForProductCategory = sectionService.sectionCorrespondsProductType(sectionId, productCategory);
        boolean sectionHasEnoughCapacity = sectionService.availableSectionCapacity(totalVolume, sectionId);

        return isSectionAppropriateForProductCategory && sectionHasEnoughCapacity;
    }
}
