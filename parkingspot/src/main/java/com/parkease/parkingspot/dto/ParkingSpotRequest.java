package com.parkease.parkingspot.dto;


import com.parkease.parkingspot.enums.SpotStatus;
import com.parkease.parkingspot.enums.SpotType;
import com.parkease.parkingspot.enums.VehicleType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ParkingSpotRequest {

    @NotNull(message = "Parking lot id is required")
    private Long lotId;

    @NotBlank(message = "Spot number is required")
    private String spotNumber;

    @NotNull(message = "Floor number is required")
    private Integer floor;

    @NotNull(message = "Spot type is required")
    private SpotType spotType;

    @NotNull(message = "Vehicle type is required")
    private VehicleType vehicleType;

    @NotNull(message = "Spot status is required")
    private SpotStatus status;

    @NotNull(message = "Handicapped flag is required")
    private Boolean handicapped;

    @NotNull(message = "EV charging flag is required")
    private Boolean evCharging;

    @NotNull(message = "Price per hour is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double pricePerHour;
}
