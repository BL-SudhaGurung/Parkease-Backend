package com.parkease.vehicle.dto;

import lombok.Data;

@Data
public class VehicleRequest {
    private Integer userId;
    private String licensePlate;
    private String make;
    private String model;
    private String color;
    private String type;
}
