package org.ernmrkc.customerservice.Exceptions;

import org.ernmrkc.customerservice.Exceptions.Model.CustomBaseException;
import org.ernmrkc.customerservice.Exceptions.Model.SimpleResponse;
import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends CustomBaseException {
    public CustomerNotFoundException() {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse("Customer not found"));
    }
}
