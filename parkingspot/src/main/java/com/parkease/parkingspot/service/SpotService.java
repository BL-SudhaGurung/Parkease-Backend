package com.parkease.parkingspot.service;

import com.parkease.parkingspot.entity.ParkingSpot;

import java.util.List;


public interface SpotService {

    ParkingSpot addSpot(ParkingSpot spot);

    List<ParkingSpot> addBulkSpots(
            List<ParkingSpot> spots);

    ParkingSpot getSpotById(Long spotId);

    List<ParkingSpot> getSpotsByLot(Long lotId);

    List<ParkingSpot> getAvailableSpots(Long lotId);

    void occupySpot(Long spotId);

    void releaseSpot(Long spotId);

    ParkingSpot updateSpot(Long spotId,
                           ParkingSpot spot);

    void deleteSpot(Long spotId);

    int countAvailable(Long lotId);
}

