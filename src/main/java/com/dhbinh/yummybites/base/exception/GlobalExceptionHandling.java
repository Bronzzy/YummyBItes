package com.dhbinh.yummybites.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandling extends RuntimeException {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ResponseBody> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<ResponseBody> responseBodies = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();
        BindingResult result = exception.getBindingResult();
        for (FieldError error : result.getFieldErrors()) {
            ResponseBody responseBody = new ResponseBody
                    (HttpStatus.BAD_REQUEST, ErrorMessage.errorKeyAndMessageMap().get(error.getDefaultMessage()), error.getDefaultMessage());
            responseBodies.add(responseBody);
        }
        return responseBodies;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<ResponseBody> handleConstraintViolationException(ConstraintViolationException exception) {
        List<ResponseBody> responseBodies = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();
        Set<ConstraintViolation<?>> result = exception.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : result) {
            ResponseBody responseBody = new ResponseBody
                    (HttpStatus.BAD_REQUEST, ErrorMessage.errorKeyAndMessageMap().get(constraintViolation.getMessage()), constraintViolation.getMessage());
            responseBodies.add(responseBody);
        }
        return responseBodies;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseBody handleMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        String message = "The parameter for " + exception.getParameterName() + " is missing";
        return new ResponseBody(HttpStatus.BAD_REQUEST, ErrorMessage.KEY_MISSING_PARAMETER, message);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InputValidationException.class)
    public ResponseBody handleInputValidationException(InputValidationException exception) {
        return exception.getResponseBody();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseBody ResourceNotFoundException(ResourceNotFoundException exception) {
        return exception.getResponseBody();
    }
}
