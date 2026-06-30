package com.parkease.vehicle.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseStructure<T> {
    int status;
    T data;
    String msg;
}
