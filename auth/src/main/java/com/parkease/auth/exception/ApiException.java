package com.parkease.auth.exception;

import com.parkease.auth.enums.ErrorCode;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public class ApiException extends RuntimeException {

 private final ErrorCode errorCode;


    public ApiException(ErrorCode errorCode) {
        super(errorCode.getDefaultMessage());
        this.errorCode = errorCode;
    }

    public ApiException(ErrorCode errorCode,String customMsg){
      super(customMsg);
      this.errorCode=errorCode;
    }
}
