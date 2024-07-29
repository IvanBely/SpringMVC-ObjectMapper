package com.example.SpringMVC_ObjectMapper.repository;


import com.example.SpringMVC_ObjectMapper.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
