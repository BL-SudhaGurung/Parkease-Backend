package com.parkease.booking.client;

import com.parkease.booking.dto.PaymentResponse;
import com.parkease.booking.dto.PaymentRequest;
import com.parkease.booking.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="payment")
public interface PayementClient {

    @PostMapping("/api/v1/payments")
    PaymentResponse createPayment(@RequestBody PaymentRequest request);

    @GetMapping("/api/v1/payments/{paymentId}")
    PaymentResponse getPayment(@PathVariable Long paymentId);
}
