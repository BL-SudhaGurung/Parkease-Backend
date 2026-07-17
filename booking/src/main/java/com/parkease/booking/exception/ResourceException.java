package com.parkease.booking.exception;

import com.parkease.booking.enums.ErrorCode;

public class ResourceException extends RuntimeException {
    private final ErrorCode errorCode;

    public ResourceException(ErrorCode errorCode) {
        super(errorCode.getDefaultMessage());
        this.errorCode = errorCode;
    }
    public ResourceException( ErrorCode errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
