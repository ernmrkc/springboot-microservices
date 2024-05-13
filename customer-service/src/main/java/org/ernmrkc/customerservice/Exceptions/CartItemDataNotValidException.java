package org.ernmrkc.customerservice.Exceptions;

import org.ernmrkc.customerservice.Exceptions.Model.CustomBaseException;
import org.ernmrkc.customerservice.Exceptions.Model.SimpleResponse;
import org.springframework.http.HttpStatus;

public class CartItemDataNotValidException extends CustomBaseException {
    public CartItemDataNotValidException(String message) {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse(message));
    }
}
