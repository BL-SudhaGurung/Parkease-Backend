package com.parkease.booking.client;

import com.parkease.booking.config.FeignConfig;
import com.parkease.booking.dto.VehicleResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "vehicle",configuration = FeignConfig.class)
public interface VehicleClient {

    @GetMapping("/api/v1/vehicles/{vehiclePlate}")
    VehicleResponseDto getVehicle( @PathVariable String vehiclePlate);
}
