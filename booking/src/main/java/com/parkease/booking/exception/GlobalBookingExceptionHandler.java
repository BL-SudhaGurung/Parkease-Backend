package com.parkease.booking.exception;

import com.parkease.booking.enums.ErrorCode;
import com.parkease.booking.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalBookingExceptionHandler {
    
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> ApiExceptionHandler(ApiException ex){
        ErrorCode errorCode=ex.getErrorCode();

        ApiResponse<ErrorResponse> response=new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(errorCode.getDefaultMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setData(null);

        return ResponseEntity
                .status(errorCode.getHttpStatus()).body(response);
        
    }


    @ExceptionHandler(ResourceException.class)
    public ResponseEntity<ApiResponse<ErrorResponse>> ResourceNotFoundHandler(ResourceException ex){
        ErrorCode errorCode=ErrorCode.RESOURCE_NOT_AVALIABLE;

        ApiResponse<ErrorResponse> response=new ApiResponse<>();
        response.setSuccess(false);
        response.setMessage(errorCode.getDefaultMessage());
        response.setTimestamp(LocalDateTime.now());
        response.setData(null);

        return ResponseEntity
                .status(errorCode.getHttpStatus()).body(response);

    }

}
