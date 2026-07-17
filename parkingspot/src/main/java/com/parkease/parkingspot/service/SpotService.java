package com.parkease.parkingspot.service;

import com.parkease.parkingspot.dto.ParkingSpotRequest;
import com.parkease.parkingspot.dto.ParkingSpotResponse;
import com.parkease.parkingspot.entity.ParkingSpot;

import java.util.List;


public interface SpotService {

    ParkingSpotResponse addSpot(ParkingSpotRequest spot);

    List<ParkingSpotResponse> addBulkSpots(
            List<ParkingSpotRequest> spots);

    ParkingSpotResponse getSpotById(Long spotId);

    List<ParkingSpotResponse> getSpotsByLot(Long lotId);

    List<ParkingSpotResponse> getAvailableSpots(Long lotId);

    void occupySpot(Long spotId);

    void releaseSpot(Long spotId);

    ParkingSpotRequest updateSpot(Long spotId,
                           ParkingSpot spot);

    void deleteSpot(Long spotId);

    int countAvailable(Long lotId);
}

