package com.dhbinh.yummybites.base.exception;

import com.dhbinh.yummybites.restaurant.entity.Restaurant;
import org.hibernate.PropertyValueException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandLing extends RuntimeException {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> RuntimeException(ValidationException exception,
                                                   WebRequest webRequest,
                                                   BindingResult result) {
        ResponseBody response = new ResponseBody();
        response.setTimeStamp(LocalDateTime.now());
        response.setMessageKey("ValidationException");

        List<String> errorMessages = new ArrayList<>();
        for (FieldError error : result.getFieldErrors()) {
            errorMessages.add(error.getDefaultMessage());
        }

        response.setMessage(errorMessages);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InputValidationException.class)
    public ResponseEntity<Object> InputValidationExceptions(InputValidationException exception,
                                                            WebRequest webRequest) {
        ResponseBody response = new ResponseBody();
        response.setTimeStamp(LocalDateTime.now());
        response.setMessageKey(Restaurant.class + ".NOT_FOUND");
//        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> IllegalArgumentException(IllegalArgumentException exception, WebRequest webRequest) {
        ResponseBody response = new ResponseBody();
        response.setTimeStamp(LocalDateTime.now());
        response.setMessageKey(Restaurant.class + ".IllegalArgumentException");
//        response.setMessage("IllegalArgumentException IllegalArgumentException");
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
