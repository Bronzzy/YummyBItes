package com.dhbinh.restaurantservice.base.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends RuntimeException {

    @Getter
    private final ResponseBody responseBody;

    public ResourceNotFoundException(String keyMessage, String message) {
        this.responseBody = new ResponseBody(HttpStatus.NOT_FOUND, keyMessage, message);
    }
}
