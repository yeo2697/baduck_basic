package com.example.baduck.handler.exception.custom;

import com.example.baduck.handler.exception.ErrorCode;
import lombok.Getter;

@Getter
public class EmailDuplicateException extends RuntimeException {
    private final ErrorCode errorCode;

    public EmailDuplicateException(String message, ErrorCode errorCode) {
       super(message);
       this.errorCode = errorCode;
    }
}
