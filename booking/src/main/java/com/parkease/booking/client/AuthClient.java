package com.parkease.booking.client;


import com.parkease.booking.dto.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name ="Auth")
public interface AuthClient {

    @GetMapping("/api/v1/auth/user/{username}")
    UserResponseDto getUserByUsername(@PathVariable String username);


}
