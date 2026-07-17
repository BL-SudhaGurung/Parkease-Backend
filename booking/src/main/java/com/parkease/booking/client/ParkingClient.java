package com.parkease.booking.client;

import com.parkease.booking.config.FeignConfig;
import com.parkease.booking.dto.ParkingLotResponseDto;
import com.parkease.booking.util.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "parkinglot",configuration = FeignConfig.class)
public interface ParkingClient {

    @GetMapping("/api/v1/lots/{lotId}")
    ParkingLotResponseDto getLot(@PathVariable Long lotId);

}
