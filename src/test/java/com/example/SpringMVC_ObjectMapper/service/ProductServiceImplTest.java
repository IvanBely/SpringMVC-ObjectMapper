package com.example.SpringMVC_ObjectMapper.service;

import com.example.SpringMVC_ObjectMapper.exception.ProductNotFoundException;
import com.example.SpringMVC_ObjectMapper.model.Product;
import com.example.SpringMVC_ObjectMapper.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId(1L);
        product.setName("Sample Product");
        product.setDescription("Description of the product");
        product.setPrice(29.99);
        product.setQuantityInStock(100);
    }

    @Test
    void getAllProducts_ReturnsProductsList() {
        when(productRepository.findAll()).thenReturn(Collections.singletonList(product));

        List<Product> result = productService.getAllBooks();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
    }

    @Test
    void getProduct_ExistingId_ReturnsProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product result = productService.getProduct(1L);
        assertNotNull(result);
        assertEquals(product, result);
    }

    @Test
    void getProduct_NonExistingId_ProductNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.getProduct(1L));
    }

    @Test
    void createProduct_ValidProduct_ReturnsCreatedProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.createProduct(product);
        assertNotNull(result);
        assertEquals(product, result);
    }

    @Test
    void updateProduct_ExistingId_ValidProduct_ReturnsUpdatedProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.updateProduct(1L, updatedProduct);
        assertNotNull(result);
        assertEquals("Updated Product", result.getName());
    }

    @Test
    void updateProduct_NonExistingId_ProductNotFoundException() {
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");

        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.updateProduct(1L, updatedProduct));
    }

    @Test
    void deleteProduct_ExistingId_ReturnsDeletedProductId() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).delete(any(Product.class));

        Long result = (long) productService.deleteProduct(1L);
        assertEquals(1L, result);
        verify(productRepository, times(1)).delete(any(Product.class));
    }

    @Test
    void deleteProduct_NonExistingId_ProductNotFoundException() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> productService.deleteProduct(1L));
    }
}
