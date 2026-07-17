package com.parkease.booking.dto;

import com.parkease.booking.enums.BookingType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;


import java.time.LocalDateTime;

@Data
public class BookingRequest {

    @NotNull(message = "Parking Lot ID is required")
    private Long lotId;

    @NotNull(message = "Parking Spot ID is required")
    private Long spotId;

    @NotBlank(message = "Vehicle plate is required")
    private String vehiclePlate;

    @NotBlank(message = "Vehicle type is required")
    private String vehicleType;

    @NotNull(message = "Booking type is required")
    private BookingType bookingType;

    @NotNull(message = "Start time is required")
    @CreatedDate
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    @LastModifiedDate
    private LocalDateTime endTime;
}
