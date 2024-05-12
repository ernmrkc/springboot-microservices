package org.ernmrkc.productservice.Exceptions;

import org.ernmrkc.productservice.Exceptions.Model.CustomBaseException;
import org.ernmrkc.productservice.Exceptions.Model.SimpleResponse;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends CustomBaseException {
    public ProductNotFoundException() {
        super(HttpStatus.BAD_REQUEST, new SimpleResponse("Product not found"));
    }
}
