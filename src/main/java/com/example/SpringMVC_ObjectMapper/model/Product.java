package com.example.SpringMVC_ObjectMapper.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotBlank(message = "Name cannot be blank")
    @NotNull(message = "Name cannot be null", groups = ValidationGroups.Create.class)
    private String name;

    @NotBlank(message = "Description cannot be blank")
    @NotNull(message = "Description cannot be null", groups = ValidationGroups.Create.class)
    private String description;

    @NotNull(message = "Price cannot be null", groups = ValidationGroups.Create.class)
    @Positive(message = "Price must be positive", groups = ValidationGroups.Create.class)
    private Double price;

    @NotNull(message = "QuantityInStock cannot be null", groups = ValidationGroups.Create.class)
    @Positive(message = "QuantityInStock must be positive", groups = ValidationGroups.Create.class)
    private Integer quantityInStock;
}
