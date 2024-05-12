package org.ernmrkc.productservice.Modules.Product.QueryHandlers;

import org.ernmrkc.productservice.Modules.Product.Models.ProductDTO;
import org.ernmrkc.productservice.Modules.Product.ProductRepository;
import org.ernmrkc.productservice.Modules.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductDTOsQueryHandler implements Query<Void, List<ProductDTO>> {
    private final ProductRepository productRepository;

    public GetAllProductDTOsQueryHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(Void input) {
        List<ProductDTO> productDTOList = productRepository.getAllProductDTOs();
        return ResponseEntity.ok().body(productDTOList);
    }
}
