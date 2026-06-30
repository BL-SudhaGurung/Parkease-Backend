package com.parkease.auth.entity;

import com.parkease.auth.enums.Role;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;


import java.time.LocalDateTime;


import lombok.*;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(name = "username")

    private String username;

    @Column(unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    private String phone;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "vehicle_plate")
    private String vehiclePlate;

    @Column(name = "is_active")
    private Boolean isActive;

    @CreatedBy
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;


    @Column(name = "profile_pic_url")
    private String profilePicUrl;

}

