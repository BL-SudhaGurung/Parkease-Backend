package com.parkease.vehicle.entity;

import com.parkease.vehicle.enums.VehicleType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicle_id")
    private Integer vehicleId;

    @Column(name = "owner_id")
    private Integer ownerId;

    @Column(unique = true, name = "license_plate")
    private String licensePlate;

    private String make;

    private String model;

    private String color;

    @Column(name = "vehicle_type")
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_ev")
    private Boolean isEv;

    @CreatedDate
    @Column(updatable = false, name = "registered_at")
    private LocalDateTime registeredAt;


}
