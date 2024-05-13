package org.ernmrkc.customerservice.Validations;

import org.ernmrkc.customerservice.Exceptions.CartItemDataNotValidException;
import org.ernmrkc.customerservice.Modules.CartItem.Models.CartItem;
import org.ernmrkc.customerservice.Services.CartItem_Cart.CartItemCartCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;

@Service
public class CartItemValidationService {
    private static final Logger logger = LoggerFactory.getLogger(CartItemCartCommandService.class);

    public void validateCartItem(CartItem cartItem, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            errorMessage += "--> Cart Item Data: " + cartItem;
            System.out.println(errorMessage);
            logger.error(errorMessage);
            throw new CartItemDataNotValidException(errorMessage);
        }
    }
}
