package com.parkease.parkinglot.service;

import com.parkease.parkinglot.entity.ParkingLot;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotImp implements ParkingLotService {
    @Override
    public ParkingLot createLot(ParkingLot parkingLot) {
        return null;
    }

    @Override
    public Optional<ParkingLot> getLotById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<ParkingLot> getLotsByCity(String city) {
        return List.of();
    }

    @Override
    public List<ParkingLot> getLotsByManager(int managerId) {
        return List.of();
    }

    @Override
    public List<ParkingLot> getNearbyLots(double latitude, double longitude) {
        return List.of();
    }

    @Override
    public ParkingLot updateLot(Long id, ParkingLot parkingLot) {
        return null;
    }

    @Override
    public void toggleOpen(Long id) {

    }

    @Override
    public void deleteLot(Long id) {

    }

    @Override
    public void decrementAvailable(int lotId) {

    }

    @Override
    public void incrementAvailable(int lotId) {

    }

    @Override
    public List<ParkingLot> searchLots(String city) {
        return List.of();
    }
}
