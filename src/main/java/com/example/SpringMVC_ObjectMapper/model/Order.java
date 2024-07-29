package com.example.SpringMVC_ObjectMapper.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;


    @ManyToOne
    @NotNull
    private Customer customer;

    @ManyToMany
    @NotNull
    private List<Product> products;

    @NotNull
    private String orderDate;

    @NotNull
    private String shippingAddress;

    @NotNull
    private double totalPrice;

    @NotNull
    private String orderStatus;

}