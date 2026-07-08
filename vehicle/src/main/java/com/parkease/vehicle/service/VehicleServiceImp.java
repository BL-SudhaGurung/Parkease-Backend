package com.parkease.vehicle.service;

import com.parkease.vehicle.client.AuthClient;
import com.parkease.vehicle.dto.UserResponse;
import com.parkease.vehicle.dto.VehicleRequest;
import com.parkease.vehicle.dto.VehicleResponse;
import com.parkease.vehicle.entity.Vehicle;
import com.parkease.vehicle.exception.ApiException;
import com.parkease.vehicle.enums.ErrorCode;
import com.parkease.vehicle.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImp implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;
    private final AuthClient authClient;

    public VehicleServiceImp(VehicleRepository vehicleRepository, ModelMapper modelMapper, AuthClient authClient) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
        this.authClient = authClient;
    }

    @Override
    public VehicleResponse addVehicle(VehicleRequest request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        System.out.println("Adding vehicle for user:"+ username);
        Integer ownerId = authClient.getUserByUsername(username).getUserId();
        System.out.println(ownerId+"owner id");

        Vehicle vehicle = modelMapper.map(request, Vehicle.class);
        vehicle.setIsActive(true);
        vehicle.setOwnerId(ownerId);
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
        if (!vehicleRepository.existsById(vehicleId))
            throw new ApiException(ErrorCode.VEHICLE_NOT_FOUND, "Vehicle not found with id: " + vehicleId);
        vehicleRepository.deleteById(vehicleId);
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
