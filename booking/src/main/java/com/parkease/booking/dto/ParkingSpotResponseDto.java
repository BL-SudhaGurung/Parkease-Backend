package com.parkease.booking.dto;

import lombok.Data;

@Data
public class ParkingSpotResponseDto {

    private Long spotId;
    private Long lotId;
    private String spotNumber;
    private String spotType;
    private boolean available;
}
