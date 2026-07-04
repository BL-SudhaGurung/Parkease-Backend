package com.parkease.auth.dto;

import com.parkease.auth.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserResponse {

    private String username;
    private String email;
    private String phone;
    private Role role;



}
