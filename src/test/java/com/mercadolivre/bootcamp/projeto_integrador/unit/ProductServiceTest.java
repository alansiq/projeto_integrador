package com.mercadolivre.bootcamp.projeto_integrador.unit;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewProductDto;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import com.mercadolivre.bootcamp.projeto_integrador.exception.InvalidProductException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.ProductRepository;
import com.mercadolivre.bootcamp.projeto_integrador.service.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    @DisplayName("it should validate that a product due date is valid")
    public void shouldValidateProductDueDate() {
        // TODO: 26/04/22 - Create test after finishing BatchStockService
        productService.validateProductDueDate(5L);
        assertTrue(true);
    }

    @Test
    @DisplayName("it should validate that a stock quantity is valid")
    public void availableStockQuantity() {
        // TODO: 26/04/22 - Create test after finishing BatchStockService
        productService.availableStockQuantity(5);
        assertTrue(true);
    }

    @Test
    @DisplayName("it should find a product by id")
    public void shouldFindProductById() {
        // Arrange tests
        Product product = Product.builder()
                                .id(5L)
                                .build();

        // Execute actions
        Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(product));
        Product result = productService.findByProductId(5L);

        // Assert result
        assertEquals(product, result);
    }

    @Test
    @DisplayName("it should find a product by name")
    public void shouldFindProductByName() {
        // Arrange tests
        Product product1 = Product.builder()
                                .productName("Product")
                                .build();

        // Execute action
        Mockito.when(productRepository.findByProductName(any())).thenReturn(Optional.of(product1));
        Product result = productService.findByProductName("Product");

        // Assert result
        assertEquals(product1, result);
    }

    @Test
    @DisplayName("it should find all by product name")
    public void shouldFindAllByProductName() {
        // Arrange tests
        Product product = Product.builder().build();
        List<Product> productList = Arrays.asList(product, product);

        // Execute action
        Mockito.when(productRepository.findAllByProductName(any())).thenReturn(productList);
        List<Product> resultList = productService.findAllByProductName("name");

        // Assert result
        assertEquals(productList, resultList);
    }

    @Test
    @DisplayName("it should not throw error when trying to find products with invalid name")
    public void shouldNotFindAllByProductName() {
        //Arrange
        List<Product> productList = Collections.emptyList();
        Mockito.when(productRepository.findAllByProductName(any())).thenReturn(productList);

        // Exec
        assertThrows(InvalidProductException.class, () -> productService.findAllByProductName("Product"));
    }

    @Test
    @DisplayName("it should find a product by category")
    public void shouldFindAllProductByCategory() {
        // Arrange tests
        Product product = Product.builder().category(any()).build();
        List<Product> productList = Arrays.asList(product, product);

        // Execute action
        Mockito.when(productRepository.findAllByCategory(Category.FRESH)).thenReturn(productList);
        List<Product> resultList = productService.findAllByCategory(Category.FRESH);

        // Assert result
        assertEquals(productList, resultList);
    }

    @Test
    @DisplayName("it should not throw error when trying to find products with invalid name")
    public void shouldNotFindAllProductByCategory() {
        //Arrange
        List<Product> productList = Collections.emptyList();
        Mockito.when(productRepository.findAllByCategory(any())).thenReturn(productList);

        // Exec
        assertThrows(InvalidProductException.class,() -> productService.findAllByCategory(Category.FRESH));
    }

    @Test
    @DisplayName("it should find a product list by salesman id")
    public void shouldFindProductListBySalesmanId() {
        // Arrange tests
        Product product = Product.builder().salesman(any()).build();
        List<Product> productList = Arrays.asList(product, product);

        // Execute action
        Mockito.when(productRepository.findAllBySalesman_Id("any")).thenReturn(productList);
        List<Product> resultList = productService.findAllBySalesmanId(any());

        // Assert result
        assertEquals(resultList, productList);
    }

    @Test
    @DisplayName("it should throw error when trying to find products with invalid salesmanid")
    public void shouldNotFindAllBySalesmanId() {
        //Arrange
        List<Product> productList = Collections.emptyList();
        Mockito.when(productRepository.findAllBySalesman_Id(any())).thenReturn(productList);

        // Exec
        assertThrows(InvalidProductException.class,() -> productService.findAllBySalesmanId("a"));
    }

    @Test
    @DisplayName("it should find a product list")
    public void shouldFindProductList() {

        // Arrange tests
        Product product = Product.builder().productName("Product 1").build();
        List<Product> productList = Arrays.asList(product, product);

        // Execute action
        Mockito.when(productRepository.findAll()).thenReturn(productList);
        List<Product> resultList = productService.findAll();

        // Assert result
        assertEquals(resultList, productList);
    }

    @Test
    @DisplayName("it should throw error when productlist is empty")
    public void shouldNotFindAllProductList() {
        //Arrange
        List<Product> productList = Collections.emptyList();
        Mockito.when(productRepository.findAll()).thenReturn(productList);

        // Exec
        assertThrows(InvalidProductException.class,() -> productService.findAll());
    }

    @Test
    @DisplayName("it should create a new product")
    public void shouldCreateProduct() {
        // Arrange tests
        NewProductDto productDto = NewProductDto.builder()
                .productName("Product")
                .category(Category.FRESH)
                .maxTemperature(1F)
                .minimumTemperature(0F)
                .salesman_id(1L)
                .volume(BigDecimal.valueOf(10))
                .build();

        Product product = Product.builder()
                .id(5L)
                .productName("Product")
                .category(Category.FRESH)
                .maxTemperature(1F)
                .minimumTemperature(0F)
                .volume(BigDecimal.valueOf(10))
                .build();
        // Execute action
        Mockito.when(productRepository.save(any())).thenReturn(product);
        Product result = productService.create(productDto);

        // Assert result
        assertEquals(product, result);
    }

    @Test
    @DisplayName("it should delete an existing product")
    public void shouldDeleteProduct() {
        // Arrange tests
        Product product = Product.builder().id(5L).build();
        // Execute action
        Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(any());
        // Assert result
        assertDoesNotThrow(() -> {
            productService.delete(5L);
        });
    }

    @Test
    @DisplayName("it should throw an exception if attempting to delete a nonexistent product")
    public void shouldNotDeleteNonExistentProduct() {
        // Arrange tests

        // Execute action

        // Assert result
        assertThrows(InvalidProductException.class, () -> productService.delete(any()));
    }
    
    @Test
    @DisplayName("it should update an existing product")
    public void shouldUpdateExistingProduct() {
        // Arrange tests
        Product product         = Product.builder().id(5L).productName("Test").build();
        Product productResponse         = Product.builder().id(5L).productName("Test2").build();

        // Execute action
        Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(any())).thenReturn(productResponse);
        Product result = productService.update(productResponse);

        // Assert result
        assertEquals(productResponse, result);
    }
}
