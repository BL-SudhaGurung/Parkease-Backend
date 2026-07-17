package com.parkease.parkingspot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

SPOT_NOT_AVAILABLE("SPOT_01","spot is not available",HttpStatus.NOT_FOUND);

private  final String code;
private final String defaultMessage;
private final HttpStatus httpStatus;

}
