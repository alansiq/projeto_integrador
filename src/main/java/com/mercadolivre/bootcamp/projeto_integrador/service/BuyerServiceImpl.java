package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewBuyerDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Buyer;
import com.mercadolivre.bootcamp.projeto_integrador.exception.buyerExceptions.BuyerIdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.buyerExceptions.BuyerListEmptyException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.BuyerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BuyerServiceImpl implements BuyerService{
    private final BuyerRepository buyerRepository;

    @Override
    public Buyer addBuyer(NewBuyerDTO newBuyerDTO) {
        Buyer buyer = Buyer.builder()
                .fullName(newBuyerDTO.getFullName())
                .build();

        return buyerRepository.save(buyer);
    }

    @Override
    public List<Buyer> getAllBuyer() {
        return findAll();
    }

    @Override
    public Buyer updateBuyer(Buyer buyer) {
        Buyer updateBuyerById = findById(buyer.getBuyerId());

        updateBuyerById.setFullName(buyer.getFullName());

        return buyerRepository.save(updateBuyerById);
    }

    @Override
    public void deleteBuyer(String buyerId) {
        Buyer foundedBuyerById = buyerRepository.findById(buyerId).orElseThrow(()
                -> new BuyerIdNotFoundException(buyerId));
        buyerRepository.delete(foundedBuyerById);
    }

    @Override
    public List<Buyer> findAll() {
        List<Buyer> buyerList = buyerRepository.findAll();
        if (buyerList.isEmpty()) throw new BuyerListEmptyException();
        return buyerList;
    }

    @Override
    public Buyer findById(String buyerId) {
        return buyerRepository.findById(buyerId).orElseThrow(()
                -> new BuyerIdNotFoundException(buyerId));
    }
}
