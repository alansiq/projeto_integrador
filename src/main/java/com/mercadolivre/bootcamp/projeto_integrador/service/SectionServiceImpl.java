package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewSectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;
import com.mercadolivre.bootcamp.projeto_integrador.exception.SectionNotFound;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.SectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    @Override
    public Section addSection(NewSectionDTO sectionDTO) {
        Section savedSectionDTO = sectionRepository.save(NewSectionDTO.convert(sectionDTO));
        return savedSectionDTO;
    }

    @Override
    public List<Section> getAllSection() {
        List<Section> list = sectionRepository.findAll();
        if(list.isEmpty()) throw new EmptyListException();
        return list;
    }

    @Override
    public Section getSectionById(Long sectionId) {
        Section getSectionById = sectionRepository.findById(sectionId).orElseThrow(() ->
                new SectionNotFound(sectionId));
        return getSectionById;
    }

    @Override
    public void deleteSection(Long sectionId) {
        try {
            sectionRepository.deleteById(sectionId);
        } catch (EmptyResultDataAccessException e) {
            new SectionNotFound(sectionId);
        }
    }

    @Override
    public Section updateSection(Section section) {
        Section getSectionId = sectionRepository.findById(section.getSectionId()).orElseThrow(() -> new SectionNotFound(section.getSectionId()));

        getSectionId.setCapacity(section.getCapacity());
        getSectionId.setCategory(section.getCategory());
        getSectionId.setCapacity(section.getCapacity());
        getSectionId.setListInBoundOrder(section.getListInBoundOrder());
        getSectionId.setWarehouseId(section.getWarehouseId());


        return sectionRepository.save(getSectionId);
    }


    @Override
    public boolean isSectionValid(Long sectionID) {
            Optional<Section> sectionOptional = sectionRepository.findById(sectionID);

            if (sectionOptional.isPresent()) {
                return true;
            }

        return false;
    }

    @Override
    public boolean availableSectionCapacity(BigDecimal totalVolume, Long sectionId) {

        Section getSection = getSectionById(sectionId);

        if (getSection.getCapacity().compareTo(totalVolume) == 0 || getSection.getCapacity().compareTo(totalVolume) < 0) {
            return false;

        }

        return true;
    }

    @Override
    public boolean sectionCorrespondsProductType(Long sectionId, Category category) {
        Section getSection = sectionRepository.findById(sectionId).orElseThrow(
                () -> new SectionNotFound(sectionId));

        if (getSection.getCategory().equals(category)) {
            return true;
        }
        return false;
    }
}
