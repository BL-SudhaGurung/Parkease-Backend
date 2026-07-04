package com.parkease.auth.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND("USR_001", "User not found", HttpStatus.NOT_FOUND),
    USER_ALREADY_EXISTS("USR_002", "User already exists", HttpStatus.CONFLICT),
    EMAIL_ALREADY_EXISTS("USR_003", "Email already in use", HttpStatus.CONFLICT),
    USERNAME_ALREADY_EXISTS("USR_004", "Username already in use", HttpStatus.CONFLICT),
    ACCOUNT_DEACTIVATED("USR_005", "Account is deactivated", HttpStatus.FORBIDDEN),

    INTERNAL_SERVER_ERROR("USER_500", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String defaultMessage;
    private final HttpStatus httpStatus;
}
