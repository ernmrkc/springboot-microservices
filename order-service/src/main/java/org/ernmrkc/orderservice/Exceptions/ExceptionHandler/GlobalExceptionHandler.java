package org.ernmrkc.orderservice.Exceptions.ExceptionHandler;

import org.ernmrkc.orderservice.Exceptions.Model.CustomBaseException;
import org.ernmrkc.orderservice.Exceptions.Model.SimpleResponse;
import org.ernmrkc.orderservice.Exceptions.OrderItemDataNotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OrderItemDataNotValidException.class)
    public ResponseEntity<SimpleResponse> handleOrderItemDataNotValidException(CustomBaseException exception){
        return ResponseEntity.status(exception.getStatus()).body(exception.getSimpleResponse());
    }
}
