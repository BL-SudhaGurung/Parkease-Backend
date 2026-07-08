package com.parkease.vehicle.client;

import com.parkease.vehicle.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "auth")
public interface AuthClient {

    @GetMapping("/api/v1/auth/user/{username}")
    UserResponse getUserByUsername(@PathVariable String username);
}
