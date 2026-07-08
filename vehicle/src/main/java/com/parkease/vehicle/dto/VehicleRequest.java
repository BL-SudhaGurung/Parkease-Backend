package com.parkease.vehicle.dto;

import com.parkease.vehicle.enums.VehicleType;
import lombok.Data;

@Data
public class VehicleRequest {
    private String licensePlate;
    private String make;
    private String model;
    private String color;
    private VehicleType vehicleType;
    private Boolean isEv;
}
