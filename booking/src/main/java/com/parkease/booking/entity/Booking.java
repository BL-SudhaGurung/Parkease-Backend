package com.parkease.booking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Booking {

    @Id
    private Long bookingId;

    private Long userId;

    private Long lotId;

    private Long spotId;

    private String vehiclePlate;

    private String vehicleType;

    @Enumerated(EnumType.STRING)
    private BookingType bookingType;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private Double totalAmount;

    private LocalDateTime createdAt;
}
