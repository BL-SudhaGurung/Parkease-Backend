package com.parkease.booking.dto;

import lombok.Data;

@Data
public class PaymentResponse{
    private Long paymentId;
    private String status;
    private Double amount;
}
