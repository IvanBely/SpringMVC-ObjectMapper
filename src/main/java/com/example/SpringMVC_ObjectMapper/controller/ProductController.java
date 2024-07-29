package com.example.SpringMVC_ObjectMapper.controller;

import com.example.SpringMVC_ObjectMapper.model.Product;
import com.example.SpringMVC_ObjectMapper.repository.ProductRepository;
import com.example.SpringMVC_ObjectMapper.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ObjectMapper defaultObjectMapper;
    private final ObjectMapper ObjectMapperNullIgnoring;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> listProducts = productService.getAllBooks();
        return new ResponseEntity<>(listProducts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody String productJson) throws JsonProcessingException {
        Product product = defaultObjectMapper.readValue(productJson, Product.class);
        Product createdProduct = productService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody String productJson) throws JsonProcessingException {
        Product product = ObjectMapperNullIgnoring.readValue(productJson, Product.class);
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteProduct(@PathVariable Long id) {
        int deleteProductId = productService.deleteProduct(id);
        return new ResponseEntity<>(deleteProductId, HttpStatus.OK);
    }
}
