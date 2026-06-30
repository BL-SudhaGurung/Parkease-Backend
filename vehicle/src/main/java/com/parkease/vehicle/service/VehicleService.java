package com.parkease.vehicle.service;

import com.parkease.vehicle.dto.VehicleRequest;
import com.parkease.vehicle.dto.VehicleResponse;

import java.util.List;

public interface VehicleService {

    VehicleResponse addVehicle(VehicleRequest request);

    VehicleResponse getVehicleById(Integer vehicleId);

    List<VehicleResponse> getVehiclesByUser(Integer userId);

    VehicleResponse updateVehicle(Integer vehicleId, VehicleRequest request);

    void deleteVehicle(Integer vehicleId);

    void deactivateVehicle(Integer vehicleId);

    VehicleResponse getVehicleByLicensePlate(String licensePlate);
}
