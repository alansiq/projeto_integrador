package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.buyer.NewBuyerDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Buyer;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.BuyerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is the service implementation of buyer entity.
 */

@Service
@AllArgsConstructor
public class BuyerServiceImpl implements BuyerService{
    private final BuyerRepository buyerRepository;

    /**
     * Create a new Buyer.
     * @param newBuyerDTO
     * @return the created buyer.
     */
    @Override
    public Buyer addBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    /**
     * Get all buyers using the method {@link #findAll() findAll}.
     * @return a buyer list.
     */
    @Override
    public List<Buyer> getAllBuyer() {
        return findAll();
    }

    /**
     * Check if Buyer id exists with {@link #findById(Long) findById} method. <br>
     * If exists then update Buyer attributes.
     * @param buyer
     * @return a updated Buyer.
     *
     */
    @Override
    public Buyer updateBuyer(Buyer buyer) {
        Buyer updateBuyerById = findById(buyer.getBuyerId());

        updateBuyerById.setFullName(buyer.getFullName());

        return buyerRepository.save(updateBuyerById);
    }

    /**
     * Check if Buyer id exists with {@link #findById(Long) findById} method. <br>
     * If exists then remove the Buyer.
     * @param buyerId
     * @return void.
     *
     */
    @Override
    public void deleteBuyer(Long buyerId) {
        Buyer foundedBuyerById = findById(buyerId);
        buyerRepository.delete(foundedBuyerById);
    }

    /**
     * Get from database all Buyers.
     * @return a Buyers list.
     * @exception EmptyListException if Buyer list is empty.
     */
    @Override
    public List<Buyer> findAll() {
        List<Buyer> buyerList = buyerRepository.findAll();
        if (buyerList.isEmpty()) throw new EmptyListException();
        return buyerList;
    }

    /**
     * Find a Buyer by Id.
     * @param buyerId
     * @return a Buyer.
     * @exception IdNotFoundException if id doesn't found.
     */
    @Override
    public Buyer findById(Long buyerId) {
        return buyerRepository.findById(buyerId).orElseThrow(()
                -> new IdNotFoundException(buyerId));
    }
}
