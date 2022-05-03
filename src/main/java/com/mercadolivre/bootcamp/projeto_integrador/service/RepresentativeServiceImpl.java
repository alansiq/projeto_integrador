package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Representative;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.inbound_order.RepresentativeNotAssociatedWithSectionException;
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
        List<Representative> representativeList = representativeRepository.findAll();
        if(representativeList.isEmpty()) throw new EmptyListException();
        return representativeList;
    }

    @Override
    public Representative getRepresentativeById(Long representativeId) {
        return representativeRepository.findById(representativeId).orElseThrow(() -> new IdNotFoundException(representativeId));
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
        if(!representative.getSectionId().equals(sectionId)) throw new RepresentativeNotAssociatedWithSectionException(representativeId, sectionId);


        return true;
    }
}
