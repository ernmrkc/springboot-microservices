package org.ernmrkc.productservice.Modules.Product.CommandHandlers;

import org.ernmrkc.productservice.Exceptions.ProductNotFoundException;
import org.ernmrkc.productservice.Modules.Command;
import org.ernmrkc.productservice.Modules.Product.Models.Product;
import org.ernmrkc.productservice.Modules.Product.Models.UpdateProductCommand;
import org.ernmrkc.productservice.Modules.Product.ProductRepository;
import org.ernmrkc.productservice.Validations.ProductValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@Service
public class UpdateProductCommandHandler implements Command<UpdateProductCommand, BindingResult, Product> {
    private final ProductRepository productRepository;
    private final ProductValidationService productValidationService;

    public UpdateProductCommandHandler(ProductValidationService productValidationService,
                                       ProductRepository productRepository) {
        this.productValidationService = productValidationService;
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<Product> execute(UpdateProductCommand updateProductCommand, BindingResult bindingResult) {
        Optional<Product> optionalProduct = productRepository.findById(updateProductCommand.getId());
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException();
        }
        productValidationService.validateProduct(updateProductCommand.getProduct(), bindingResult);
        Product product = updateProductCommand.getProduct();
        product.setId(updateProductCommand.getId());
        productRepository.save(product);
        return ResponseEntity.ok().body(product);
    }
}
