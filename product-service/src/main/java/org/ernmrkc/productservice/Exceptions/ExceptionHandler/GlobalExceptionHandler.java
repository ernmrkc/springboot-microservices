package org.ernmrkc.productservice.Exceptions.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.ernmrkc.productservice.Exceptions.Model.CustomBaseException;
import org.ernmrkc.productservice.Exceptions.Model.SimpleResponse;
import org.ernmrkc.productservice.Exceptions.ProductNotFoundException;
import org.ernmrkc.productservice.Exceptions.ProductNotValidException;
import org.ernmrkc.productservice.Modules.Product.Models.Category;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotValidException.class)
    public ResponseEntity<SimpleResponse> handleProductNotValidException(CustomBaseException exception){
        return ResponseEntity.status(exception.getStatus()).body(exception.getSimpleResponse());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<SimpleResponse> handleProductNotFoundException(CustomBaseException exception){
        return ResponseEntity.status(exception.getStatus()).body(exception.getSimpleResponse());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<SimpleResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        Throwable cause = exception.getCause();
        if (cause instanceof InvalidFormatException ife) {
            if (Category.class.equals(ife.getTargetType())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SimpleResponse("Product category must be one of the following: " +
                        "CELL_PHONE, COMPUTERS, TABLETS, TV_HOME_THEATER, COMPUTER_COMPONENTS, HOME_KITCHEN, PERSONAL_CARE, " +
                        "CAMERAS_DRONES, CLOTHING_ACCESSORIES, VIDEO_GAMES, CONSOLES_HEADSETS"));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SimpleResponse("JSON parse error: " + exception.getMessage()));
    }
}
