package org.ernmrkc.productservice.Validations;

import org.ernmrkc.productservice.Exceptions.ProductNotValidException;
import org.ernmrkc.productservice.Modules.Product.CommandHandlers.CreateProductCommandHandler;
import org.ernmrkc.productservice.Modules.Product.Models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;

@Service
public class ProductValidationService {
    private static final Logger logger = LoggerFactory.getLogger(CreateProductCommandHandler.class);

    public void validateProduct(Product product, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            errorMessage += "--> Product Data: " + product;
            System.out.println(errorMessage);
            logger.error(errorMessage);
            throw new ProductNotValidException(errorMessage);
        }
    }
}
