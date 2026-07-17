package com.parkease.booking.dto;

import lombok.Data;

@Data
public class PaymentRequest{

    private Long bookingId;
    private Double amount;
}
