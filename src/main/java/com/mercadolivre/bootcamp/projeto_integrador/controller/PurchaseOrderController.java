package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order.NewPurchaseOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order.PurchaseOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrder;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItems;
import com.mercadolivre.bootcamp.projeto_integrador.factory.PurchaseOrderFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PurchaseOrderController.baseUri)
@AllArgsConstructor
public class PurchaseOrderController {

    public static final String baseUri =  "/api/v1/fresh-products/purchaseOrder";

    private final PurchaseOrderFactory purchaseOrderFactory;

    @PostMapping
    public ResponseEntity<PurchaseOrder> createNewPurchaseOrder(@RequestBody NewPurchaseOrderDTO newPurchaseOrderDTO){
        PurchaseOrder purchaseOrder = purchaseOrderFactory.createNewPurchaseOrder(newPurchaseOrderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrder);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrderItems>>listPurchaseOrders(@RequestParam Long orderNumber){
        List<PurchaseOrderItems> purchaseOrderItems = purchaseOrderFactory.getPurchaseOrderItems(orderNumber);
        return ResponseEntity.ok().body(purchaseOrderItems);
    }

    @PutMapping
    public ResponseEntity<PurchaseOrder> updatePurchaseOrder(@RequestBody PurchaseOrder purchaseOrder){
        PurchaseOrder updatepurchaseOrder = purchaseOrderFactory.updatePurchaseOrder(purchaseOrder);
        return ResponseEntity.status(HttpStatus.OK).body(updatepurchaseOrder);
    }
}
