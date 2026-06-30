package com.parkease.vehicle.resource;

import com.parkease.vehicle.dto.VehicleRequest;
import com.parkease.vehicle.dto.VehicleResponse;
import com.parkease.vehicle.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleResource {


    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleResponse> addVehicle(@RequestBody VehicleRequest request) {
        return ResponseEntity.ok(vehicleService.addVehicle(request));
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<?> getVehicle(@PathVariable Integer vehicleId) {
        return ResponseEntity.ok(vehicleService.getVehicleById(vehicleId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getVehiclesByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(vehicleService.getVehiclesByUser(userId));
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<?> updateVehicle(@PathVariable Integer vehicleId, @RequestBody VehicleRequest request) {
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicleId, request));
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Integer vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    @PatchMapping("/{vehicleId}/deactivate")
    public ResponseEntity<?> deactivateVehicle(@PathVariable Integer vehicleId) {
        vehicleService.deactivateVehicle(vehicleId);
        return ResponseEntity.ok("Vehicle deactivated successfully");
    }

    @GetMapping("/plate/{licensePlate}")
    public ResponseEntity<?> getByPlate(@PathVariable String licensePlate) {
        return ResponseEntity.ok(vehicleService.getVehicleByLicensePlate(licensePlate));
    }
}
