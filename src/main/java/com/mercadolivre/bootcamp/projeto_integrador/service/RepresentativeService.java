package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Representative;

import java.util.List;

public interface RepresentativeService {
    public List<Representative> getAllRepresentatives();

    public Representative getRepresentativeById(Long representativeId);

    public Representative createRepresentative(Representative representative);

    public Representative updateRepresentative(Long representativeId, Representative representative);

    public void deleteRepresentative(Long representativeId);

    public boolean isRepresentativeAssociatedWithSection(Long representativeId, Long sectionId);

}