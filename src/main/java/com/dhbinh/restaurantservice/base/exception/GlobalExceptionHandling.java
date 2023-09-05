package com.dhbinh.restaurantservice.base.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandling extends RuntimeException {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandling.class);

    //EXCEPTION HANDLING FOR BEAN VALIDATION
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ResponseBody> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.warn(String.valueOf(e));
        List<ResponseBody> responseBodies = new ArrayList<>();
        List<String> errorMessages = new ArrayList<>();
        BindingResult result = e.getBindingResult();
        for (FieldError error : result.getFieldErrors()) {
            String logMessage = String.format(
                    "Validation error: Field '%s' in %s, rejected value: %s, message: %s",
                    error.getField(), error.getObjectName(), error.getRejectedValue(), error.getDefaultMessage());
            logger.warn(logMessage);
            ResponseBody responseBody = new ResponseBody
                    (HttpStatus.BAD_REQUEST,
                            ErrorMessage.errorKeyAndMessageMap().get(error.getDefaultMessage()) == null ?
                                    ErrorMessage.KEY_ENUM_INVALID_VALUE :
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
            String logMessage = String.format(
                    "Constraint violation: Property '%s' in %s, invalid value: %s, message: %s",
                    constraintViolation.getPropertyPath(), constraintViolation.getRootBeanClass().getName(),
                    constraintViolation.getInvalidValue(), constraintViolation.getMessage());
            logger.warn(logMessage);
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
        StackTraceElement[] stackTraceArray = e.getStackTrace();
        String logMessage = String.format("%s:%d - %s",
                stackTraceArray[0].getClassName(),
                stackTraceArray[0].getLineNumber(),
                message);
        logger.warn(logMessage);

        return new ResponseBody(HttpStatus.BAD_REQUEST, ErrorMessage.KEY_MISSING_PARAMETER, message);
    }

    //   EXCEPTION HANDLING FOR INVALID ENUM VALUE
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseBody handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        StackTraceElement[] stackTraceArray = e.getStackTrace();
        String logMessage = String.format("%s:%d - %s",
                stackTraceArray[0].getClassName(),
                stackTraceArray[0].getLineNumber(),
                e.getMessage());
        logger.warn(logMessage);

        return new ResponseBody(HttpStatus.BAD_REQUEST,
                ErrorMessage.errorKeyAndMessageMap().get(e.getMessage()),
                e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InputValidationException.class)
    public ResponseBody handleInputValidationException(InputValidationException e) {
        StackTraceElement[] stackTraceArray = e.getStackTrace();
        String logMessage = String.format("%s:%d - %s",
                stackTraceArray[0].getClassName(),
                stackTraceArray[0].getLineNumber(),
                e.getResponseBody().getErrorMessage());
        logger.warn(logMessage);

        return e.getResponseBody();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseBody handleResourceNotFoundException(ResourceNotFoundException e) {
        StackTraceElement[] stackTraceArray = e.getStackTrace();
        String logMessage = String.format("%s:%d - %s",
                stackTraceArray[0].getClassName(),
                stackTraceArray[0].getLineNumber(),
                e.getResponseBody().getErrorMessage());
        logger.warn(logMessage);

        return e.getResponseBody();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IOException.class)
    public ResponseBody handleIOException(IOException e) {
        StackTraceElement[] stackTraceArray = e.getStackTrace();
        String logMessage = String.format("%s:%d - %s",
                stackTraceArray[0].getClassName(),
                stackTraceArray[0].getLineNumber(),
                e.getMessage());
        logger.warn(logMessage);

        return new ResponseBody(HttpStatus.NOT_FOUND,
                ErrorMessage.errorKeyAndMessageMap().get(e.getMessage()),
                e.getMessage());
    }
}
