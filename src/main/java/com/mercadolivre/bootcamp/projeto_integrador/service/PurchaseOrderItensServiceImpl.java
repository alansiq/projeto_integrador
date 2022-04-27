package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewPurchaseOrderItensDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItens;
import com.mercadolivre.bootcamp.projeto_integrador.exception.InvalidProductException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.PurchaseOrderItensExceptions.PurchaseOrderItensListEmptyException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.PurchaseOrderItensExceptions.PurchaseOrderItensNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.PurchaseOrderItensRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PurchaseOrderItensServiceImpl implements PurchaseOrderItensService{
    private final PurchaseOrderItensRepository purchaseOrderItensRepository;

    @Override
    public PurchaseOrderItens addPurchaseOrderItens(NewPurchaseOrderItensDTO purchaseOrderItensDTO) {
        PurchaseOrderItens purchaseOrderItens = PurchaseOrderItens.builder()
                .purchaseOrderNumber(purchaseOrderItensDTO.getPurchaseOrderNumber())
                .productId(purchaseOrderItensDTO.getProductId())
                .quantity(purchaseOrderItensDTO.getQuantity())
                .build();

        return purchaseOrderItensRepository.save(purchaseOrderItens);
    }

    @Override
    public List<PurchaseOrderItens> getAllPurchaseOrderItens() {
        return findAll();
    }

    @Override
    public PurchaseOrderItens updatePurchaseOrderItens(PurchaseOrderItens purchaseOrderItens) {
        PurchaseOrderItens updatePurchaseOrderItens = findById(purchaseOrderItens.getPurchaseOrderItensId());

        updatePurchaseOrderItens.setPurchaseOrderNumber(purchaseOrderItens.getPurchaseOrderNumber());
        updatePurchaseOrderItens.setProductId(purchaseOrderItens.getProductId());
        updatePurchaseOrderItens.setQuantity(purchaseOrderItens.getQuantity());

        return purchaseOrderItensRepository.save(updatePurchaseOrderItens);
    }

    @Override
    public void deletePurchaseOrderItens(String purchaseOrderItensId) {
        PurchaseOrderItens purchaseOrderItens = findById(purchaseOrderItensId);
        purchaseOrderItensRepository.delete(purchaseOrderItens);
    }

    @Override
    public List<PurchaseOrderItens> findAll() {
        List<PurchaseOrderItens> purchaseOrderItensList = purchaseOrderItensRepository.findAll();
        if (purchaseOrderItensList.isEmpty()) throw new PurchaseOrderItensListEmptyException();
        return purchaseOrderItensList;
    }

    @Override
    public PurchaseOrderItens findById(String purchaseOrderItensId) {
        return purchaseOrderItensRepository.findById(purchaseOrderItensId).orElseThrow(()
                -> new PurchaseOrderItensNotFoundException(purchaseOrderItensId));
    }


}
