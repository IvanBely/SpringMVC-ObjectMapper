package com.example.SpringMVC_ObjectMapper.service;

import com.example.SpringMVC_ObjectMapper.exception.InsufficientDataException;
import com.example.SpringMVC_ObjectMapper.exception.ProductNotFoundException;
import com.example.SpringMVC_ObjectMapper.model.Product;
import com.example.SpringMVC_ObjectMapper.repository.ProductRepository;
import com.example.SpringMVC_ObjectMapper.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public List<Product> getAllBooks() {
        return productRepository.findAll();
    }
    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
    }

    @Override
    public Product createProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        return productRepository.save(existingProduct);
    }

    @Override
    public int deleteProduct(Long id) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + id));
        productRepository.delete(existingProduct);
        return Math.toIntExact(id);
    }
}
