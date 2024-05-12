package org.ernmrkc.productservice.Modules.Product.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Product name cannot be null")
    @Size(max = 50, message = "Product name must not exceed 50 characters")
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 150, message = "Product description must not exceed 100 characters")
    @Column(name = "description")
    private String description;

    @NotBlank(message = "Product price cannot be null")
    @Min(value = 0, message = "Price must be more than 0")
    @Column(name = "price", nullable = false)
    private Double price;

    @NotBlank(message = "Product stock cannot be null")
    @Min(value = 0, message = "Stock value must be more than 0")
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @Size(max = 30, message = "Product manufacturer must not exceed 30 characters")
    @Column(name = "manufacturer")
    private String manufacturer;

    @NotBlank(message = "Product category cannot be null")
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private Category category;

}
