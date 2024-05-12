package org.ernmrkc.productservice.Modules.Product.CommandHandlers;

import org.ernmrkc.productservice.Exceptions.ProductNotFoundException;
import org.ernmrkc.productservice.Modules.Command;
import org.ernmrkc.productservice.Modules.Product.Models.Product;
import org.ernmrkc.productservice.Modules.Product.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteProductCommandHandler implements Command<UUID, Void, Void> {
    private final ProductRepository productRepository;

    public DeleteProductCommandHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Void> execute(UUID id, Void bindingResult) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException();
        }
        Product product = optionalProduct.get();
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }
}
