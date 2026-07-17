package com.parkease.booking.dto;

import lombok.Data;

@Data
public class UserResponseDto {
    private Long userId;
    private String fullName;
    private String email;
    private String role;
    private boolean active;
}
