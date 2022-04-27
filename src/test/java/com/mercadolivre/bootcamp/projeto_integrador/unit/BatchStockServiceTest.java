package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import com.mercadolivre.bootcamp.projeto_integrador.exception.BatchStockIdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.InvalidProductException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.BatchStockRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.BatchStockService;
import com.mercadolivre.bootcamp.projeto_integrador.service.BatchStockServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BatchStockServiceTest {
    @Mock
    private BatchStockRepository repository;

    @InjectMocks
    private BatchStockServiceImpl service;

    Long id = 1L;
    Long productId = 1L;
    BatchStock batchStock1 = BatchStock.builder().batchNumber(1L).product(Product.builder().id(productId).build()).build();
    BatchStock batchStock2 = BatchStock.builder().batchNumber(2L).product(Product.builder().id(productId).build()).build();
    BatchStock batchStock3 = BatchStock.builder().batchNumber(3L).product(Product.builder().id(productId).build()).build();
    List<BatchStock> batchStockList = Arrays.asList(batchStock1,batchStock2,batchStock3);


    @Test
    @DisplayName("it should calculate totalVolume of given BatchStock")
    public void shouldCalculateTotalVolume() {
        // Arrange
        Product product = Product.builder().volume(BigDecimal.valueOf(10)).build();
        BatchStock batchStock = BatchStock.builder().currentQuantity(5).product(product).build();

        // Exec
        BigDecimal result = service.calculateTotalVolume(batchStock);

        // Assert
        assertEquals(BigDecimal.valueOf(50), result);
    }

    @Test
    @DisplayName("It should do create a new BatchStock.")
    public void shouldCreateBatchStock(){

        Mockito.when(repository.save(Mockito.any(BatchStock.class))).thenReturn(batchStock1);

        BatchStock result = service.create(batchStock1);

        assertEquals(batchStock1, result);
    }

    @Test
    @DisplayName("It should do list all BatchStocks.")
    public void shouldListAllBatchStocks(){

        Mockito.when(repository.findAll()).thenReturn(batchStockList);

        List<BatchStock> result = service.list();

        assertEquals(batchStockList, result);
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("It should do delete BatchStock by id.")
    public void shouldDeleteBatchStockById(){

        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(batchStock1));
        doNothing().when(repository).delete(any());

        assertDoesNotThrow(()->service.remove(id));
    }

    @Test
    @DisplayName("It should not do delete BatchStock by id when it not exists.")
    public void shouldNotDeleteBatchStockByIdWhenIdNotExist(){
        assertThrows(BatchStockIdNotFoundException.class,()->service.remove(anyLong()));
    }

    @Test
    public void shouldUpdateBatchStock(){

        BatchStock updatedBatchStock = BatchStock.builder()
                .batchNumber(1L)
                .currentQuantity(20)
                .build();

        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(batchStock1));
        Mockito.when(repository.save(Mockito.any(BatchStock.class))).thenReturn(updatedBatchStock);

        BatchStock result = service.update(updatedBatchStock);

        assertEquals(updatedBatchStock, result);
    }

    @Test
    @DisplayName("It should do find BatchStock by id.")
    public void shouldFindBatchStockById(){

        Mockito.when(repository.findById(anyLong())).thenReturn(Optional.ofNullable(batchStock1));

        BatchStock result = service.findById(id);

        assertEquals(batchStock1, result);
    }

    @Test
    @DisplayName("It should not do find BatchStock by id when it not exists.")
    public void shouldNotFindBatchStockByIdWhenIdNotExists(){
        assertThrows(BatchStockIdNotFoundException.class,()->service.findById(anyLong()));
    }

    @Test
    @DisplayName("It should do list all BatchStocks by product id.")
    public void shouldListBatchStockByProductId(){

        Mockito.when(repository.findAllByProduct_Id(anyLong())).thenReturn(batchStockList);

        List<BatchStock> result = service.findAllByProductId(productId);

        assertEquals(batchStockList, result);
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("It should not do list all BatchStocks by product id when it not exists.")
    public void shouldNotListBatchStockByProductIdWhenProductIdNotExists(){
        assertThrows(InvalidProductException.class, () -> service.findAllByProductId(anyLong()));
    }
}
