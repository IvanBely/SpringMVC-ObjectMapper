package com.example.SpringMVC_ObjectMapper.repository;

import com.example.SpringMVC_ObjectMapper.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}