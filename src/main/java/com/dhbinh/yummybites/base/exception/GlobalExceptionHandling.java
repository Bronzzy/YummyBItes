package com.dhbinh.yummybites.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
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

    //EXCEPTION HANDLING FOR BEAN VALIDATION
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ResponseBody> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ResponseBody> responseBodies = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();
        BindingResult result = e.getBindingResult();
        for (FieldError error : result.getFieldErrors()) {
            ResponseBody responseBody = new ResponseBody
                    (HttpStatus.BAD_REQUEST,
                            ErrorMessage.errorKeyAndMessageMap().get(error.getDefaultMessage()),
                            error.getDefaultMessage());
            responseBodies.add(responseBody);
        }
        return responseBodies;
    }

    //EXCEPTION HANDLING FOR ARGUMENTS VALIDATION
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public List<ResponseBody> handleConstraintViolationException(ConstraintViolationException e) {
        List<ResponseBody> responseBodies = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();
        Set<ConstraintViolation<?>> result = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : result) {
            ResponseBody responseBody = new ResponseBody
                    (HttpStatus.BAD_REQUEST,
                            ErrorMessage.errorKeyAndMessageMap().get(constraintViolation.getMessage()),
                            constraintViolation.getMessage());
            responseBodies.add(responseBody);
        }
        return responseBodies;
    }

    //EXCEPTION HANDLING FOR MISSING REQUEST PARAMETER
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseBody handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        String message = "The parameter for " + e.getParameterName() + " is missing";
        return new ResponseBody(HttpStatus.BAD_REQUEST, ErrorMessage.KEY_MISSING_PARAMETER, message);
    }

    //EXCEPTION HANDLING FOR INVALID ENUM VALUE
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseBody handleHttpMessageNotReadableException(HttpMessageNotReadableException e){
       return new ResponseBody(HttpStatus.BAD_REQUEST,
               ErrorMessage.errorKeyAndMessageMap().get(e.getMessage()),
               e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InputValidationException.class)
    public ResponseBody handleInputValidationException(InputValidationException e) {
        return e.getResponseBody();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseBody ResourceNotFoundException(ResourceNotFoundException e) {
        return e.getResponseBody();
    }
}
