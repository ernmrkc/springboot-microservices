package org.ernmrkc.productservice.Modules.Product.QueryHandlers;

import org.ernmrkc.productservice.Exceptions.ProductNotFoundException;
import org.ernmrkc.productservice.Modules.Product.Models.Product;
import org.ernmrkc.productservice.Modules.Product.Models.ProductStockDTO;
import org.ernmrkc.productservice.Modules.Product.ProductRepository;
import org.ernmrkc.productservice.Modules.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class GetProductStockValueQueryHandler implements Query<UUID, ProductStockDTO> {
    private final ProductRepository productRepository;

    public GetProductStockValueQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductStockDTO> execute(UUID id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()){
            throw new ProductNotFoundException();
        }
        ProductStockDTO productStockDTO = new ProductStockDTO(optionalProduct.get());
        return ResponseEntity.ok().body(productStockDTO);
    }
}
