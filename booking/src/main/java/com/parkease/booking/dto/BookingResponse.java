package com.parkease.booking.dto;

import com.parkease.booking.enums.BookingStatus;
import com.parkease.booking.enums.BookingType;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import java.time.LocalDateTime;

@Data
public class BookingResponse {

    private Long bookingId;

    private Long userId;

    private Long lotId;

    private Long spotId;

    private String vehiclePlate;

    private String vehicleType;

    private BookingType bookingType;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private BookingStatus status;

    private Double totalAmount;

    @CreatedBy
    private LocalDateTime createdAt;
}
