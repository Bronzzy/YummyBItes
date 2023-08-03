package com.dhbinh.yummybites.base.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
public class ResponseBody {

    private final String errorKey;
    private final String errorMessage;
    private final Integer statusCode;
    private final String statusMessage;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timeStamp;

    private final UUID uuid;

    public ResponseBody(HttpStatus status, String errorKey, String errorMessage) {
        this.errorKey = errorKey;
        this.errorMessage = errorMessage;
        this.uuid = UUID.randomUUID();
        this.statusCode = status.value();
        this.statusMessage = status.name();
        this.timeStamp = LocalDateTime.now();
    }

}
