package com.parkease.vehicle.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    VEHICLE_NOT_FOUND("VEHICLE_001", "Vehicle not found", HttpStatus.NOT_FOUND),
    VEHICLE_ALREADY_EXISTS("VEHICLE_002", "Vehicle already exists", HttpStatus.CONFLICT),
    VEHICLE_INACTIVE("VEHICLE_003", "Vehicle is inactive", HttpStatus.BAD_REQUEST),
    INVALID_LICENSE_PLATE("VEHICLE_004", "Invalid license plate", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("VEHICLE_500", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String defaultMessage;
    private final HttpStatus httpStatus;
}
