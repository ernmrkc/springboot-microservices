package org.ernmrkc.productservice.Modules.Product.Models;

import lombok.Data;

import java.util.UUID;

@Data
public class UpdateProductCommand {
    private UUID id;
    private Product product;

    public UpdateProductCommand(UUID id, Product product){
        this.id = id;
        this.product = product;
    }
}
