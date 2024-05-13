package org.ernmrkc.customerservice.Exceptions.ExceptionHandler;

import org.ernmrkc.customerservice.Exceptions.*;
import org.ernmrkc.customerservice.Exceptions.Model.CustomBaseException;
import org.ernmrkc.customerservice.Exceptions.Model.SimpleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerDataNotValidException.class)
    public ResponseEntity<SimpleResponse> handleCustomerDataNotValidException(CustomBaseException exception){
        return ResponseEntity.status(exception.getStatus()).body(exception.getSimpleResponse());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<SimpleResponse> handleCustomerNotFoundException(CustomBaseException exception){
        return ResponseEntity.status(exception.getStatus()).body(exception.getSimpleResponse());
    }

    @ExceptionHandler(AddressDataNotValidException.class)
    public ResponseEntity<SimpleResponse> handleAddressDataNotValidException(CustomBaseException exception){
        return ResponseEntity.status(exception.getStatus()).body(exception.getSimpleResponse());
    }

    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<SimpleResponse> handleAddressNotFoundException(CustomBaseException exception){
        return ResponseEntity.status(exception.getStatus()).body(exception.getSimpleResponse());
    }

    @ExceptionHandler(CartItemDataNotValidException.class)
    public ResponseEntity<SimpleResponse> handleCartItemDataNotValidException(CustomBaseException exception){
        return ResponseEntity.status(exception.getStatus()).body(exception.getSimpleResponse());
    }

    @ExceptionHandler(CartItemNotFoundException.class)
    public ResponseEntity<SimpleResponse> handleCartItemNotFoundException(CustomBaseException exception){
        return ResponseEntity.status(exception.getStatus()).body(exception.getSimpleResponse());
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity<SimpleResponse> handleCartNotFoundException(CustomBaseException exception){
        return ResponseEntity.status(exception.getStatus()).body(exception.getSimpleResponse());
    }
}
