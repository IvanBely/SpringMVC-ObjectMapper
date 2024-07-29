package com.example.SpringMVC_ObjectMapper.controller;

import com.example.SpringMVC_ObjectMapper.model.Product;
import com.example.SpringMVC_ObjectMapper.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setup() {
        product = new Product();
        product.setProductId(1L);
        product.setName("Sample Product");
        product.setDescription("Description of the product");
        product.setPrice(29.99);
        product.setQuantityInStock(100);
    }

    @Test
    public void getAllProducts_ReturnsProductsList() throws Exception {
        when(productService.getAllBooks()).thenReturn(Collections.singletonList(product));

        mockMvc.perform(get("/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value(1L))
                .andExpect(jsonPath("$[0].name").value("Sample Product"))
                .andExpect(jsonPath("$[0].description").value("Description of the product"))
                .andExpect(jsonPath("$[0].price").value(29.99))
                .andExpect(jsonPath("$[0].quantityInStock").value(100));
    }

    @Test
    public void getProductById_ExistingId_ReturnsProduct() throws Exception {
        when(productService.getProduct(1L)).thenReturn(product);

        mockMvc.perform(get("/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1L))
                .andExpect(jsonPath("$.name").value("Sample Product"))
                .andExpect(jsonPath("$.description").value("Description of the product"))
                .andExpect(jsonPath("$.price").value(29.99))
                .andExpect(jsonPath("$.quantityInStock").value(100));
    }

    @Test
    public void createProduct_ValidProduct_ReturnsCreatedProduct() throws Exception {
        when(productService.createProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productId").value(1L))
                .andExpect(jsonPath("$.name").value("Sample Product"))
                .andExpect(jsonPath("$.description").value("Description of the product"))
                .andExpect(jsonPath("$.price").value(29.99))
                .andExpect(jsonPath("$.quantityInStock").value(100));
    }

    @Test
    public void updateProduct_ExistingId_ValidProduct_ReturnsUpdatedProduct() throws Exception {
        when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(product);

        mockMvc.perform(put("/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(1L))
                .andExpect(jsonPath("$.name").value("Sample Product"))
                .andExpect(jsonPath("$.description").value("Description of the product"))
                .andExpect(jsonPath("$.price").value(29.99))
                .andExpect(jsonPath("$.quantityInStock").value(100));
    }

    @Test
    public void deleteProduct_ExistingId_ReturnsDeletedProductId() throws Exception {
        Long productId = 1L;
        when(productService.deleteProduct(productId)).thenReturn(Math.toIntExact(productId));

        mockMvc.perform(delete("/products/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(productId.toString()));
    }
}
