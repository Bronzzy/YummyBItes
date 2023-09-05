package com.dhbinh.restaurantservice.base.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class InputValidationException extends RuntimeException {

    @Getter
    private final ResponseBody responseBody;

    public InputValidationException(String keyMessage, String message) {
        this.responseBody = new ResponseBody(HttpStatus.BAD_REQUEST, keyMessage, message);
    }
}
