package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Representative;
import com.mercadolivre.bootcamp.projeto_integrador.exception.RepresentativeNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.RepresentativeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class RepresentativeServiceImpl implements RepresentativeService {

    private final RepresentativeRepository representativeRepository;

    @Override
    public List<Representative> getAllRepresentatives() {

        return representativeRepository.findAll();

    }

    @Override
    public Representative getRepresentativeById(Long representativeId) {

        return representativeRepository.findById(representativeId).orElseThrow(() -> new RepresentativeNotFoundException(representativeId));

    }

    @Override
    public Representative createRepresentative(Representative representative) {
        return representativeRepository.save(representative);
    }

    @Override
    public Representative updateRepresentative(Long representativeId, Representative representative) {

        Representative representativeAux = getRepresentativeById(representativeId);

        representativeAux.setFullName(representative.getFullName());
        representativeAux.setSectionId(representative.getSectionId());

        return representativeRepository.save(representativeAux);

    }

    @Override
    public void deleteRepresentative(Long representativeId) {
        Representative representative = getRepresentativeById(representativeId);
        representativeRepository.delete(representative);

    }


    public boolean isRepresentativeAssociatedWithSection(Long representativeId, Long sectionId) {
        Representative representative = getRepresentativeById(representativeId);
        boolean representativeIsAssociated = false;
        representativeIsAssociated = representative.getSectionId().equals(sectionId);
        return representativeIsAssociated;
    }

}