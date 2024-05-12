package org.ernmrkc.productservice.Modules.Product.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductDTO {
    private UUID id;
    private String name;
    private Double price;
    private Integer stock;

    public ProductDTO(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
    }
}
