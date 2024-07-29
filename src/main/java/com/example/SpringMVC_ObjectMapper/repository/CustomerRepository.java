package com.example.SpringMVC_ObjectMapper.repository;

import com.example.SpringMVC_ObjectMapper.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
