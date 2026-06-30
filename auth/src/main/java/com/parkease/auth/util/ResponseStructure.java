package com.parkease.auth.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ResponseStructure<T> {

    int status;
    T data;
    String msg;
}
