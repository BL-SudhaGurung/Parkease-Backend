package com.parkease.booking.client;

import com.parkease.booking.config.FeignConfig;
import com.parkease.booking.dto.ParkingSpotResponseDto;
import com.parkease.booking.util.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "parkingspot",configuration = FeignConfig.class)
public interface SpotClient {

    @GetMapping("/api/v1/spots/{spotId}")
    ParkingSpotResponseDto getSpot(@PathVariable Long spotId);

    @PutMapping("/api/v1/spots/{spotId}/occupy")
    void occupySpot(@PathVariable Long spotId);

    @PutMapping("/api/v1/spots/{spotId}/release")
    void releaseSpot(@PathVariable Long spotId);
}
