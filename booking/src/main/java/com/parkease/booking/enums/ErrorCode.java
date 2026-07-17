package com.parkease.booking.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    BOOKING_NOT_AVAILABLE("BOOK_01","booking is not available", HttpStatus.NOT_FOUND);

    private  final String code;
    private final String defaultMessage;
    private final HttpStatus httpStatus;
}
