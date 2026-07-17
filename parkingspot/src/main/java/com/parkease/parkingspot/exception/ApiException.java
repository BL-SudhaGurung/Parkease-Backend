package com.parkease.parkingspot.exception;

import com.parkease.parkingspot.enums.ErrorCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
  private final ErrorCode errorCode;


  public ApiException(ErrorCode errorCode) {
        super(errorCode.getDefaultMessage());
        this.errorCode=errorCode;
    }

    public ApiException(ErrorCode errorCode,String customMessage){
      super(customMessage);
      this.errorCode=errorCode;
    }

}
