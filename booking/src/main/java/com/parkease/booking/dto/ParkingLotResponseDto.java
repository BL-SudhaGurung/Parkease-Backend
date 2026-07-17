package com.parkease.booking.dto;

import lombok.Data;

@Data
public class ParkingLotResponseDto {
    private Long lotId;
    private String name;
    private String address;
    private String city;
}
