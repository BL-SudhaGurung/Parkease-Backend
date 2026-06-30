package com.parkease.vehicle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleResponse {
    private Integer vehicleId;
    private String licensePlate;
    private String make;
    private String model;
    private String color;
    private String type;
    private Boolean isActive;
    private boolean isEv;
}
