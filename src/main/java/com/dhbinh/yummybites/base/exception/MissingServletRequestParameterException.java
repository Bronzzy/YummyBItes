package com.dhbinh.yummybites.base.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class MissingServletRequestParameterException extends RuntimeException {

    @Getter
    private final ResponseBody responseBody;

    public MissingServletRequestParameterException(String keyMessage, String message) {
        this.responseBody = new ResponseBody(HttpStatus.BAD_REQUEST, keyMessage, message);
    }
}
