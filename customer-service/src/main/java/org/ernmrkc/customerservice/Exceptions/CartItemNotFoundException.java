package org.ernmrkc.customerservice.Exceptions;

import org.ernmrkc.customerservice.Exceptions.Model.CustomBaseException;
import org.ernmrkc.customerservice.Exceptions.Model.SimpleResponse;
import org.springframework.http.HttpStatus;

public class CartItemNotFoundException extends CustomBaseException {
    public CartItemNotFoundException() {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse("Cart item not found"));
    }
}
