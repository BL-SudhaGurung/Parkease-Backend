package com.parkease.parkingspot.repository;

import com.parkease.parkingspot.entity.ParkingSpot;
import com.parkease.parkingspot.enums.SpotStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpotRepository extends JpaRepository<SpotRepository,Integer> {
    List<ParkingSpot> findByLotId(Long lotId);

    List<ParkingSpot> findByLotIdAndStatus(
            Long lotId,
            SpotStatus status);

    int countByLotIdAndStatus(
            Long lotId,
            SpotStatus status);

    boolean existsByLotIdAndSpotNumber(
            Long lotId,
            String spotNumber);
}
