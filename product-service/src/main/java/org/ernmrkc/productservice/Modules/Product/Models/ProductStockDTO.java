package org.ernmrkc.productservice.Modules.Product.Models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductStockDTO {
    private UUID id;
    private Integer stock;

    public ProductStockDTO(Product product){
        this.id = product.getId();
        this.stock = product.getStock();
    }
}
