package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.buyer.NewBuyerDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Buyer;

import java.util.List;

public interface BuyerService {
    Buyer addBuyer(Buyer buyer);
    List<Buyer> getAllBuyer();
    List<Buyer> findAll();
    Buyer findById(Long buyerId);
    Buyer updateBuyer(Buyer buyer);
    void deleteBuyer(Long buyerId);
}
