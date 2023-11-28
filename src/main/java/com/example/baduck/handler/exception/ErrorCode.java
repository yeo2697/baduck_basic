package com.example.baduck.handler.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    EMAIL_DUPLICATION(400,"USER-ERROR-400","EMAIL_DUPLICATED"),
    EMAIL_NOT_FOUND(404,"USER-ERROR-404","EMAIL_NOT_FOUND"),
    NOT_FOUND(404,"COMMON-ERROR-404","NOT_FOUND"),
    INTER_SERVER_ERROR(500,"COMMON-ERROR-500","INTERNAL_SERVER_ERROR"),
    ;

    private final int status;
    private final String errorCode;
    private final String message;
}
