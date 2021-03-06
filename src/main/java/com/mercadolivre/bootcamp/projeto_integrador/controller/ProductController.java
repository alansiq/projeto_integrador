package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.product.NewProductDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.product.ProductDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.factory.ProductFactory;
import com.mercadolivre.bootcamp.projeto_integrador.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This class represents InBoundOrder controller.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/fresh-products/product")
public class ProductController {
    private final ProductService productService;
    private final ProductFactory productFactory;

    /**
     *
     * @param productDto Dto received in request body to create a new Product
     * @return 201 CREATED
     */
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody NewProductDTO productDto) {
        ProductDTO createdProduct = ProductDTO.convert(productFactory.createProduct(productDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /**
     *
     * @return 200 OK
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts(){
        List<ProductDTO> productList = ProductDTO.convert(productService.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    /**
     *
     * @param category Category object to filter a product list.
     * @return 200 OK
     */
    @GetMapping("/list")
    public ResponseEntity<List<ProductDTO>> getProductsByCategory(@RequestParam Category category) {
        List<ProductDTO> productList = ProductDTO.convert(productService.findAllByCategory(category));
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

}
