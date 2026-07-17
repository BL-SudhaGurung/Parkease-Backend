package com.parkease.booking.dto;

import lombok.Data;

@Data
public class VehicleResponseDto {
    private Long vehicleId;
    private Long ownerId;
    private String vehiclePlate;
    private String vehicleType;
}
