package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewProductDto;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import com.mercadolivre.bootcamp.projeto_integrador.exception.InvalidProductException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    public Boolean availableStockQuantity(Integer orderProductQuantity) {
        // TODO: 25/04/22 Verify BatchStock by product id and return quantity available;
        return null;
    }

    @Override
    public Boolean validateProductDueDate(Long productId) {
        // TODO: 26/04/22 validate product due date after batchstock endpoints are complete 
        return null;
    }

    // METHOD TO CREATE PRODUCT
    @Override
    public Product create(NewProductDto newProductDto) {

        Product product = Product.builder()
                                .productName(newProductDto.getProductName())
                                .volume(newProductDto.getVolume())
                                .minimumTemperature(newProductDto.getMinimumTemperature())
                                .maxTemperature(newProductDto.getMaxTemperature())
                                .category(newProductDto.getCategory())
                                // TODO: 25/04/22  ADD SALESMAN TO THIS METHOD
                                //  .salesman()
                                .build();

        return productRepository.save(product);
    }

    // Method to update product

    @Override
    public Product update(Product receivedProduct) {

        Product currentProduct = productRepository.findById(receivedProduct.getId()).orElseThrow(() -> new InvalidProductException(receivedProduct.getId()));

        currentProduct.setProductName(receivedProduct.getProductName());
        currentProduct.setCategory(receivedProduct.getCategory());
        currentProduct.setVolume(receivedProduct.getVolume());
        currentProduct.setMinimumTemperature(receivedProduct.getMinimumTemperature());
        currentProduct.setMaxTemperature(receivedProduct.getMaxTemperature());

        return productRepository.save(currentProduct);
    }


    // METHOD TO DELETE PRODUCT
    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new InvalidProductException(id));
        productRepository.delete(product);
    }

    @Override
    public Product findByProductId(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new InvalidProductException(id));
    }

    // METHODS TO FIND PRODUCT OR LIST OF PRODUCTS
    @Override
    public Product findByProductName(String productName) {
        return productRepository.findByProductName(productName).orElseThrow(() -> new InvalidProductException(productName));
    }

    @Override
    public List<Product> findAllByProductName(String productName) {
        List<Product> productList = productRepository.findAllByProductName(productName);
        if (productList.isEmpty()) throw new InvalidProductException(productName);

        return productList;
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        List<Product> productList = productRepository.findAllByCategory(category);
        if (productList.isEmpty()) throw new InvalidProductException(category.toString());
        return productList;
    }

    @Override
    public List<Product> findAllBySalesmanId(String salesmanId) {
        List<Product> productList = productRepository.findAllBySalesman_Id(salesmanId);
        if (productList.isEmpty()) throw new InvalidProductException(salesmanId);
        return productList;
    }

    @Override
    public List<Product> findAll() {
        List<Product> list = productRepository.findAll();
        if(list.isEmpty()) throw new EmptyListException();
        return list;
    }
}
