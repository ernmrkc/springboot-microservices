package org.ernmrkc.customerservice.Exceptions;

import org.ernmrkc.customerservice.Exceptions.Model.CustomBaseException;
import org.ernmrkc.customerservice.Exceptions.Model.SimpleResponse;
import org.springframework.http.HttpStatus;

public class CartNotFoundException extends CustomBaseException {
    public CartNotFoundException() {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse("Cart not found"));
    }
}
