package com.example.SpringMVC_ObjectMapper.service;

import com.example.SpringMVC_ObjectMapper.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllBooks();

    Product getProduct(Long id);

    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    int deleteProduct(Long id);
}
