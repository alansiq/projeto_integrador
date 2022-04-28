package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrder;
import com.mercadolivre.bootcamp.projeto_integrador.exception.PurchaseOrderIdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.PurchaseOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

    private final PurchaseOrderRepository repository;

    @Override
    public PurchaseOrder create(PurchaseOrder purchaseOrder) {
        return repository.save(purchaseOrder);
    }

    @Override
    public List<PurchaseOrder> list() {
        return repository.findAll();
    }

    @Override
    public PurchaseOrder findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new PurchaseOrderIdNotFoundException(id));
    }

    @Override
    public PurchaseOrder update(PurchaseOrder purchaseOrder) {
        PurchaseOrder updatedPurchaseOrder = findById(purchaseOrder.getPurchaseOrderNumber());
        if(purchaseOrder.getPurchaseOrderNumber() != null){
            updatedPurchaseOrder.setPurchaseOrderDate(purchaseOrder.getPurchaseOrderDate());
        }
        if(purchaseOrder.getStatusOrder() != null) {
            updatedPurchaseOrder.setStatusOrder(purchaseOrder.getStatusOrder());
        }
        if(purchaseOrder.getBuyer().getBuyerId() != null) {
            updatedPurchaseOrder.getBuyer().setBuyerId(purchaseOrder.getBuyer().getBuyerId());
        }
        return repository.save(updatedPurchaseOrder);
    }

    @Override
    public void remove(Long id) {
        PurchaseOrder purchaseOrder = findById(id);
        repository.delete(purchaseOrder);
    }
}