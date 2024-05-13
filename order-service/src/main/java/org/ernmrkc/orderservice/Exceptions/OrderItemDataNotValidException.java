package org.ernmrkc.orderservice.Exceptions;

import org.ernmrkc.orderservice.Exceptions.Model.CustomBaseException;
import org.ernmrkc.orderservice.Exceptions.Model.SimpleResponse;
import org.springframework.http.HttpStatus;

public class OrderItemDataNotValidException extends CustomBaseException {
    public OrderItemDataNotValidException(String message) {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse(message));
    }
}
