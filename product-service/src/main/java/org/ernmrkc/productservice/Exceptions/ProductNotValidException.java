package org.ernmrkc.productservice.Exceptions;

import org.ernmrkc.productservice.Exceptions.Model.CustomBaseException;
import org.ernmrkc.productservice.Exceptions.Model.SimpleResponse;
import org.springframework.http.HttpStatus;

public class ProductNotValidException extends CustomBaseException {
    public ProductNotValidException(String message) {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse(message));
    }
}
