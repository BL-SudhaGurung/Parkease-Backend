package com.parkease.parkinglot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;


import java.time.LocalDate;


@Entity
@Table(name = "parkinglot")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int lotId;
    private String name;
    private String address;
    private String city;
    private double latitude;
    private double longitude;
    private int totalSpots;
    private int availableSpots;

    @Column(name = "manager_id")
    private int managerId;
    @Column(name = "is_open")
    private boolean isOpen;
    @Column(name = "open_time")
    private LocalDate openTime;
    @Column(name = "close_time")
    private LocalDate closeTime;

    @Column(name = "image_url")
    private String imageUrl;
}
