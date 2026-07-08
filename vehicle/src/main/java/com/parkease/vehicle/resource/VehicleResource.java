package com.parkease.vehicle.resource;

import com.parkease.vehicle.dto.VehicleRequest;
import com.parkease.vehicle.dto.VehicleResponse;
import com.parkease.vehicle.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleResource {


    private final VehicleService vehicleService;



    @PreAuthorize("hasRole('DRIVER')")
    @PostMapping("/add")
    public ResponseEntity<VehicleResponse> addVehicle(@RequestBody VehicleRequest request) {
        return ResponseEntity.ok(vehicleService.addVehicle(request));
    }

    @PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
    @GetMapping("/{vehicleId}")
    public ResponseEntity<?> getVehicle(@PathVariable Integer vehicleId) {
        return ResponseEntity.ok(vehicleService.getVehicleById(vehicleId));
    }

    @PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getVehiclesByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(vehicleService.getVehiclesByUser(userId));
    }

    @PreAuthorize("hasRole('DRIVER')")
    @PutMapping("/{vehicleId}")
    public ResponseEntity<?> updateVehicle(@PathVariable Integer vehicleId, @RequestBody VehicleRequest request) {
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicleId, request));
    }

    @PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Integer vehicleId) {
        vehicleService.deleteVehicle(vehicleId);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{vehicleId}/deactivate")
    public ResponseEntity<?> deactivateVehicle(@PathVariable Integer vehicleId) {
        vehicleService.deactivateVehicle(vehicleId);
        return ResponseEntity.ok("Vehicle deactivated successfully");
    }

    @PreAuthorize("hasAnyRole('DRIVER', 'ADMIN')")
    @GetMapping("/plate/{licensePlate}")
    public ResponseEntity<?> getByPlate(@PathVariable String licensePlate) {
        return ResponseEntity.ok(vehicleService.getVehicleByLicensePlate(licensePlate));
    }
}
