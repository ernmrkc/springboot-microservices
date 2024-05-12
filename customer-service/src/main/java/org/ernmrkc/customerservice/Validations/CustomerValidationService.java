package org.ernmrkc.customerservice.Validations;

import org.ernmrkc.customerservice.Exceptions.CustomerDataNotValidException;
import org.ernmrkc.customerservice.Modules.Customer.CommandHandlers.CreateCustomerCommandHandler;
import org.ernmrkc.customerservice.Modules.Customer.Models.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;

@Service
public class CustomerValidationService {
    private static final Logger logger = LoggerFactory.getLogger(CreateCustomerCommandHandler.class);

    public void validateCustomer(Customer customer, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            errorMessage += "--> Customer Data: " + customer;
            System.out.println(errorMessage);
            logger.error(errorMessage);
            throw new CustomerDataNotValidException(errorMessage);
        }
    }

}
