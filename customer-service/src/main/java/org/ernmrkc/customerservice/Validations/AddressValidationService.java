package org.ernmrkc.customerservice.Validations;

import org.ernmrkc.customerservice.Exceptions.CustomerDataNotValidException;
import org.ernmrkc.customerservice.Modules.Address.CommandHandlers.CreateAddressCommandHandler;
import org.ernmrkc.customerservice.Modules.Address.Models.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;

@Service
public class AddressValidationService {
    private static final Logger logger = LoggerFactory.getLogger(CreateAddressCommandHandler.class);

    public void validateAddress(Address address, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            String errorMessage = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            errorMessage += "--> Address Data: " + address;
            System.out.println(errorMessage);
            logger.error(errorMessage);
            throw new CustomerDataNotValidException(errorMessage);
        }
    }
}
