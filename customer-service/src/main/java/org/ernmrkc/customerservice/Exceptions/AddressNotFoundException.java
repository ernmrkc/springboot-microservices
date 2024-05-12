package org.ernmrkc.customerservice.Exceptions;

import org.ernmrkc.customerservice.Exceptions.Model.CustomBaseException;
import org.ernmrkc.customerservice.Exceptions.Model.SimpleResponse;
import org.springframework.http.HttpStatus;

public class AddressNotFoundException extends CustomBaseException {
    public AddressNotFoundException() {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse("Address not found"));
    }
}
