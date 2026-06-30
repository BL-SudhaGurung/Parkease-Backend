package com.parkease.parkinglot.service;

import com.parkease.parkinglot.entity.ParkingLot;

import java.util.List;
import java.util.Optional;


public interface ParkingLotService {

    ParkingLot createLot(ParkingLot parkingLot);

    Optional<ParkingLot> getLotById(Long id);

    List<ParkingLot> getLotsByCity(String city);

    List<ParkingLot> getLotsByManager(int managerId);

    List<ParkingLot> getNearbyLots(double latitude,
                                   double longitude);

    ParkingLot updateLot(Long id,
                         ParkingLot parkingLot);

    void toggleOpen(Long id);

    void deleteLot(Long id);

    void decrementAvailable(int lotId);

    void incrementAvailable(int lotId);

    List<ParkingLot> searchLots(String city);

}
