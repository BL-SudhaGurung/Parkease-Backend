package com.parkease.vehicle.dto;

import lombok.Data;

@Data
public class UserResponse {
    private Integer userId;
    private String username;
    private String email;
}
