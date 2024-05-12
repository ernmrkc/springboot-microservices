package org.ernmrkc.productservice.Modules.Product.QueryHandlers;

import org.ernmrkc.productservice.Modules.Product.Models.Product;
import org.ernmrkc.productservice.Modules.Product.ProductRepository;
import org.ernmrkc.productservice.Modules.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsQueryHandler implements Query<Void, List<Product>> {
    private final ProductRepository productRepository;

    public GetAllProductsQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<Product>> execute(Void input) {
        List<Product> productList = productRepository.findAll();
        return ResponseEntity.ok().body(productList);
    }
}
