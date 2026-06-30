package com.parkease.parkingspot.entity;

import com.parkease.parkingspot.enums.SpotStatus;
import com.parkease.parkingspot.enums.SpotType;
import com.parkease.parkingspot.enums.VehicleType;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long spotId;

    @Column(name = "lot_id")
    private Long lotId;

    @Column(name = "spot_number")
    private String spotNumber;

    @Column(name = "floor_number")
    private Integer floor;

    @Enumerated(EnumType.STRING)
    @Column(name = "spot_type")
    private SpotType spotType;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_type")
    private VehicleType vehicleType;

    @Enumerated(EnumType.STRING)
    private SpotStatus status;

    private Boolean handicapped;

    @Column(name = "ev_charging")
    private Boolean evCharging;

    @Column(name = "price_per_hour")
    private Double pricePerHour;
}
