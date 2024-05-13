package org.ernmrkc.orderservice.Validations;

import org.ernmrkc.orderservice.Exceptions.OrderItemDataNotValidException;
import org.ernmrkc.orderservice.Modules.OrderItem.CommandHandlers.CreateOrderItemQueryHandler;
import org.ernmrkc.orderservice.Modules.OrderItem.Models.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;

@Service
public class OrderItemValidationService {
    private static final Logger logger = LoggerFactory.getLogger(CreateOrderItemQueryHandler.class);

    public void validateOrderItem(OrderItem orderItem, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            errorMessage += "--> Order Item Data: " + orderItem;
            System.out.println(errorMessage);
            logger.error(errorMessage);
            throw new OrderItemDataNotValidException(errorMessage);
        }
    }
}
