package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewSectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;

import java.math.BigDecimal;
import java.util.List;

public interface SectionService {
    Section addSection(NewSectionDTO sectionDTO);
    List<Section> getAllSection();
    Section getSectionById(Long sectionId);
    void deleteSection(Long sectionId);
    Section updateSection(Section section);
    boolean isSectionValid(Long sectionID);
    boolean availableSectionCapacity(BigDecimal totalVolume, Long sectionId);
    boolean sectionCorrespondsProductType(Long sectionId, Category category);
}
