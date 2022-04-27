package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderService {
    PurchaseOrder create(PurchaseOrder purchaseOrder);
    List<PurchaseOrder> listAll();
    PurchaseOrder findById(Long id);
    PurchaseOrder update(PurchaseOrder purchaseOrder);
    void remove(Long id);
}
