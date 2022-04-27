package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrder;
import com.mercadolivre.bootcamp.projeto_integrador.entity.StatusOrder;
import com.mercadolivre.bootcamp.projeto_integrador.exception.BatchStockIdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.PurchaseOrderIdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.PurchaseOrderRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.PurchaseOrderServiceImpl;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class PurchaseOrderServiceTest {

    @Mock
    private PurchaseOrderRepository repository;

    @InjectMocks
    private PurchaseOrderServiceImpl service;

    Long id = 1L;
    PurchaseOrder purchaseOrder1 = PurchaseOrder.builder().purchaseOrderNumber(1L).statusOrder(StatusOrder.CART).build();
    PurchaseOrder purchaseOrder2 = PurchaseOrder.builder().purchaseOrderNumber(2L).statusOrder(StatusOrder.CART).build();
    PurchaseOrder purchaseOrder3 = PurchaseOrder.builder().purchaseOrderNumber(3L).statusOrder(StatusOrder.CART).build();


    @Test
    @DisplayName("It should do create a new PurchaseOrder.")
    public void shouldCreatePurchaseOrder() {

        Mockito.when(repository.save(Mockito.any(PurchaseOrder.class))).thenReturn(purchaseOrder1);

        PurchaseOrder result = service.create(purchaseOrder1);

        assertEquals(purchaseOrder1, result);
    }

    @Test
    @DisplayName("It should do list all PurchaseOrders.")
    public void shouldFindAllPurchaseOrders() {
        List<PurchaseOrder> purchaseOrderList = new ArrayList<>();
        purchaseOrderList.add(purchaseOrder1);
        purchaseOrderList.add(purchaseOrder2);
        purchaseOrderList.add(purchaseOrder3);

        Mockito.when(repository.findAll()).thenReturn(purchaseOrderList);

        List<PurchaseOrder> result = service.listAll();

        assertEquals(purchaseOrderList, result);
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("It should do find PurchaseOrder by id.")
    public void shouldFindPurchaseOrderById() {
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(purchaseOrder1));

        PurchaseOrder result = service.findById(id);

        assertEquals(purchaseOrder1, result);
    }

    @Test
    @DisplayName("It should not do find PurchaseOrder by id when it not exists.")
    public void shouldNotFindPurchaseOrderByIdWhenIdNotExists() {
        assertThrows(PurchaseOrderIdNotFoundException.class,()->service.findById(anyLong()));
    }

    @Test
    @DisplayName("It should do update a PurchaseOrder.")
    public void shouldUpdatePurchaseOrder() {
        PurchaseOrder updatedPurchaseOrder = PurchaseOrder.builder()
                .purchaseOrderNumber(1L)
                .statusOrder(StatusOrder.PROCESSING)
                .build();

        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(purchaseOrder1));
        Mockito.when(repository.save(Mockito.any(PurchaseOrder.class))).thenReturn(updatedPurchaseOrder);

        PurchaseOrder result = service.update(updatedPurchaseOrder);

        assertEquals(updatedPurchaseOrder, result);
    }

    @Test
    @DisplayName("It should do delete a PurchaseOrder.")
    public void shouldDeletePurchaseOrder() {
        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(purchaseOrder1));
        doNothing().when(repository).delete(any());

        assertDoesNotThrow(()->service.remove(id));
    }

    @Test
    @DisplayName("It should not do delete a PurchaseOrder when id not exists.")
    public void shouldNotDeletePurchaseOrderWhenIdNotExists() {
        assertThrows(PurchaseOrderIdNotFoundException.class,()->service.remove(anyLong()));
    }

}