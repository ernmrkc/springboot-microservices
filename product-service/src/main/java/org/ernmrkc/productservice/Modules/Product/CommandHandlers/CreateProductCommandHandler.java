package org.ernmrkc.productservice.Modules.Product.CommandHandlers;

import org.ernmrkc.productservice.Modules.Command;
import org.ernmrkc.productservice.Modules.Product.Models.Product;
import org.ernmrkc.productservice.Modules.Product.Models.ProductDTO;
import org.ernmrkc.productservice.Modules.Product.ProductRepository;
import org.ernmrkc.productservice.Validations.ProductValidationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class CreateProductCommandHandler implements Command<Product, BindingResult, ProductDTO> {
    private final ProductRepository productRepository;
    private final ProductValidationService productValidationService;

    public CreateProductCommandHandler(ProductRepository productRepository,
                                       ProductValidationService productValidationService) {
        this.productRepository = productRepository;
        this.productValidationService = productValidationService;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Product product, BindingResult bindingResult) {
        productValidationService.validateProduct(product, bindingResult);
        productRepository.save(product);
        return ResponseEntity.ok().body(new ProductDTO(product));
    }
}
