package com.parkease.auth.dto;

import com.parkease.auth.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;

import java.time.LocalDateTime;

@Data
public class UserRegistration {

    @NotBlank(message = "Username is required")
    private String username;
    @Email(message = "Invalid email format")
    @NotNull(message = "Email is required")
    private String email;
    @NotBlank
    private String phone;
    @NotNull(message = "Role is required")
    private Role role;

    @NotBlank()
    @Size(min=8 ,max=30,message = "Password must be at least 8 characters ")
    private String password;


}
