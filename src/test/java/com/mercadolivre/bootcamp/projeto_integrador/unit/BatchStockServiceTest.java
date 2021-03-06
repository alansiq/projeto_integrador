package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.entity.*;
import com.mercadolivre.bootcamp.projeto_integrador.exception.product.InvalidProductException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.BatchStockRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.BatchStockServiceImpl;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@Tag("unit")
@ExtendWith(MockitoExtension.class)
public class BatchStockServiceTest {
    @Mock
    private BatchStockRepository batchStockRepository;

    @InjectMocks
    private BatchStockServiceImpl batchStockService;

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
        Integer currentQuantity = 5;
        BigDecimal volume = BigDecimal.valueOf(10);
        BigDecimal expectedVolume = volume.multiply(BigDecimal.valueOf(currentQuantity));
        Product product = Product.builder().volume(volume).build();
        BatchStock batchStock = BatchStock.builder().currentQuantity(currentQuantity).product(product).build();

        // Exec
        BigDecimal result = batchStockService.calculateTotalVolume(batchStock);

        // Assert
        assertEquals(expectedVolume, result);
    }

    @Test
    @DisplayName("It should do create a new BatchStock.")
    public void shouldCreateBatchStock(){

        Mockito.when(batchStockRepository.save(Mockito.any(BatchStock.class))).thenReturn(batchStock1);

        BatchStock result = batchStockService.create(batchStock1);

        assertEquals(batchStock1, result);
    }

    @Test
    @DisplayName("It should do list all BatchStocks.")
    public void shouldListAllBatchStocks(){

        Mockito.when(batchStockRepository.findAll()).thenReturn(batchStockList);

        List<BatchStock> result = batchStockService.findAll();

        assertEquals(batchStockList, result);
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("It should do delete BatchStock by id.")
    public void shouldDeleteBatchStockById(){

        Mockito.when(batchStockRepository.findById(anyLong())).thenReturn(Optional.ofNullable(batchStock1));
        doNothing().when(batchStockRepository).delete(any());

        assertDoesNotThrow(()-> batchStockService.remove(id));
    }

    @Test
    @DisplayName("It should not do delete BatchStock by id when it not exists.")
    public void shouldNotDeleteBatchStockByIdWhenIdNotExist(){
        assertThrows(IdNotFoundException.class,()-> batchStockService.remove(anyLong()));
    }

    @Test
    public void shouldUpdateBatchStock(){

        BatchStock updatedBatchStock = BatchStock.builder()
                .batchNumber(1L)
                .currentQuantity(20)
                .build();

        Mockito.when(batchStockRepository.findById(anyLong())).thenReturn(Optional.ofNullable(batchStock1));
        Mockito.when(batchStockRepository.save(Mockito.any(BatchStock.class))).thenReturn(updatedBatchStock);

        BatchStock result = batchStockService.update(updatedBatchStock);

        assertEquals(updatedBatchStock, result);
    }

    @Test
    @DisplayName("It should do find BatchStock by id.")
    public void shouldFindBatchStockById(){

        Mockito.when(batchStockRepository.findById(anyLong())).thenReturn(Optional.ofNullable(batchStock1));

        BatchStock result = batchStockService.findById(id);

        assertEquals(batchStock1, result);
    }

    @Test
    @DisplayName("It should not do find BatchStock by id when it not exists.")
    public void shouldNotFindBatchStockByIdWhenIdNotExists(){
        assertThrows(IdNotFoundException.class,()-> batchStockService.findById(anyLong()));
    }

    @Test
    @DisplayName("It should do list all BatchStocks by product id.")
    public void shouldListBatchStockByProductId(){

        Mockito.when(batchStockRepository.findAllByProduct_Id(anyLong())).thenReturn(batchStockList);

        List<BatchStock> result = batchStockService.findAllByProductId(productId);

        assertEquals(batchStockList, result);
        assertEquals(3, result.size());
    }

    @Test
    @DisplayName("It should not do list all BatchStocks by product id when it not exists.")
    public void shouldNotListBatchStockByProductIdWhenProductIdNotExists(){
        assertThrows(InvalidProductException.class, () -> batchStockService.findAllByProductId(anyLong()));
    }

    @Test
    @DisplayName("it should order batchstock list by product dueDate")
    public void shouldOrderByDueDate() {
        // Arrange
        BatchStock batchStock1 = BatchStock.builder()
                .dueDate(LocalDate.now().plusDays(10)).build();
        BatchStock batchStock2 = BatchStock.builder()
                .dueDate(LocalDate.now().plusDays(20)).build();
        BatchStock batchStock3 = BatchStock.builder()
                .dueDate(LocalDate.now().plusDays(30)).build();

        List<BatchStock> unorderedList = Arrays.asList(batchStock3, batchStock1, batchStock2);
        List<BatchStock> orderedList = Arrays.asList(batchStock1, batchStock2, batchStock3);

        // Act
        List<BatchStock> result = batchStockService.orderBatchStockList(unorderedList);

        // Assert
        assertEquals(orderedList, result);
    }

    @Test
    @DisplayName("it should get batchstock list by sectionId and dueDate")
    public void shouldGetBatchStockListFromSectionIdAndDueDate() {
        // TODO: 30/04/22

        // Arrange
        int daysFromToday = 10;
        LocalDate dueDate = LocalDate.now().plusDays(daysFromToday);
        long sectionId = 1L;

        Section section = Section.builder().sectionId(sectionId).build();
        BatchStock batchStock = BatchStock.builder().section(section).dueDate(dueDate).build();
        List<BatchStock> batchStockList = Arrays.asList(batchStock, batchStock, batchStock);

        // Act
        Mockito.when(batchStockRepository.findByDueDateIsLessThanEqualAndSection_SectionId(any(), any())).thenReturn(batchStockList);
        List<BatchStock> result = batchStockService.findAllBySectionIdAndDueDate(daysFromToday, sectionId);

        // Assert
        assertEquals(sectionId, batchStockList.get(1).getSection().getSectionId());
        assertEquals(batchStockList, result);
    }

    @Test
    @DisplayName("it should get batchstock list by custom date and category")
    public void shouldGetBatchstockListFromCustomDateAndProductCategory() {
        // TODO: 30/04/22
        // Arrange
        int daysFromToday = 10;
        LocalDate dueDate = LocalDate.now().plusDays(daysFromToday);
        long sectionId = 1L;

        Section section = Section.builder().sectionId(sectionId).build();
        BatchStock batchStock = BatchStock.builder().section(section).dueDate(dueDate).build();
        List<BatchStock> batchStockList = Arrays.asList(batchStock, batchStock, batchStock);

        // Act
        Mockito.when(batchStockRepository.findByDueDateLessThanEqualAndProduct_Category(any(), any())).thenReturn(batchStockList);
        List<BatchStock> result = batchStockService.findAllByDueDateAndProductCategory(daysFromToday, Category.FRESH);

        //Assert
        assertEquals(batchStockList, result);
    }

    @Test
    @DisplayName("it should select a valid batchStock by product quantity and price")
    public void shouldSelectAValidBatchStockByQuantityAndPrice() {
        // Arrange
        Long productId = 1L;
        Integer quantity = 10;
        BigDecimal price = BigDecimal.valueOf(50);
        Product product = Product.builder().id(productId).build();

        BatchStock batchStock = BatchStock.builder().price(price).currentQuantity(quantity).product(product).build();

        PurchaseOrderItems purchaseOrderItems = PurchaseOrderItems.builder()
                .productId(productId)
                .quantity(quantity)
                .build();

        // Act
        Mockito.when(batchStockRepository.findByCurrentQuantityIsGreaterThanEqualAndProduct_IdAndDueDateIsGreaterThanEqual(any(), any(), any())).thenReturn(batchStock);
        BatchStock result = batchStockService.selectBatchStock(purchaseOrderItems);

        // Assert
        assertEquals(batchStock.getPrice(), result.getPrice());

    }
}
