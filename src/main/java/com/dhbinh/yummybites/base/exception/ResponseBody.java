package com.dhbinh.yummybites.base.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ResponseBody {

    private String messageKey;

    private List<String> message;

    private LocalDateTime timeStamp;

    public ResponseBody(String messageKey, List<String> message, LocalDateTime timeStamp) {
        this.messageKey = messageKey;
        this.message = message;
        this.timeStamp = timeStamp;
    }

}
