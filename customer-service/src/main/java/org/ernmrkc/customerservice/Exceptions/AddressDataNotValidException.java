package org.ernmrkc.customerservice.Exceptions;

import org.ernmrkc.customerservice.Exceptions.Model.CustomBaseException;
import org.ernmrkc.customerservice.Exceptions.Model.SimpleResponse;
import org.springframework.http.HttpStatus;

public class AddressDataNotValidException extends CustomBaseException {
    public AddressDataNotValidException(String message) {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse(message));
    }
}
