package com.parkease.vehicle.service;

import com.parkease.vehicle.dto.VehicleRequest;
import com.parkease.vehicle.dto.VehicleResponse;
import com.parkease.vehicle.entity.Vehicle;
import com.parkease.vehicle.exception.ApiException;
import com.parkease.vehicle.enums.ErrorCode;
import com.parkease.vehicle.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImp implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    @Override
    public VehicleResponse addVehicle(VehicleRequest request) {
        Vehicle vehicle = modelMapper.map(request, Vehicle.class);
        vehicle.setIsActive(true);
        return modelMapper.map(vehicleRepository.save(vehicle), VehicleResponse.class);
    }

    @Override
    public VehicleResponse getVehicleById(Integer vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ApiException(ErrorCode.VEHICLE_NOT_FOUND, "Vehicle not found with id: " + vehicleId));
        return modelMapper.map(vehicle, VehicleResponse.class);
    }

    @Override
    public List<VehicleResponse> getVehiclesByUser(Integer ownerId) {
        return vehicleRepository.findAllByOwnerId(ownerId)
                .stream()
                .map(v -> modelMapper.map(v, VehicleResponse.class))
                .toList();
    }

    @Override
    public VehicleResponse updateVehicle(Integer vehicleId, VehicleRequest request) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ApiException(ErrorCode.VEHICLE_NOT_FOUND, "Vehicle not found with id: " + vehicleId));
        modelMapper.map(request, vehicle);
        return modelMapper.map(vehicleRepository.save(vehicle), VehicleResponse.class);
    }

    @Override
    public void deleteVehicle(Integer vehicleId) {
        vehicleRepository.deleteByVehicleId(vehicleId);
    }

    @Override
    public void deactivateVehicle(Integer vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new ApiException(ErrorCode.VEHICLE_NOT_FOUND, "Vehicle not found with id: " + vehicleId));
        vehicle.setIsActive(false);
        vehicleRepository.save(vehicle);
    }

    @Override
    public VehicleResponse getVehicleByLicensePlate(String licensePlate) {
        Vehicle vehicle = vehicleRepository.findByLicensePlate(licensePlate)
                .orElseThrow(() -> new ApiException(ErrorCode.VEHICLE_NOT_FOUND, "Vehicle not found with plate: " + licensePlate));
        return modelMapper.map(vehicle, VehicleResponse.class);
    }
}
