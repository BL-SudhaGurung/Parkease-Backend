package com.parkease.parkingspot.resource;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/spots")
public class ParkingSpotResource {

    private final SpotService spotService;

    /**
     * Add Single Parking Spot
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','LOT_MANAGER')")
    public ResponseEntity<SpotResponse> addSpot(
            @Valid @RequestBody SpotRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(spotService.addSpot(request));
    }

    /**
     * Add Multiple Parking Spots
     */
    @PostMapping("/bulk")
    @PreAuthorize("hasAnyRole('ADMIN','LOT_MANAGER')")
    public ResponseEntity<List<SpotResponse>> addBulk(
            @Valid @RequestBody List<SpotRequest> requests) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(spotService.addBulk(requests));
    }

    /**
     * Get Spot By Id
     */
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<SpotResponse> getById(
            @PathVariable Integer id) {

        return ResponseEntity.ok(spotService.getById(id));
    }

    /**
     * Get All Spots By Parking Lot
     */
    @GetMapping("/lot/{lotId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<SpotResponse>> getByLot(
            @PathVariable Integer lotId) {

        return ResponseEntity.ok(spotService.getByLot(lotId));
    }

    /**
     * Get Available Spots
     */
    @GetMapping("/lot/{lotId}/available")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<SpotResponse>> getAvailable(
            @PathVariable Integer lotId) {

        return ResponseEntity.ok(spotService.getAvailable(lotId));
    }

    /**
     * Get Spots By Type
     */
    @GetMapping("/lot/{lotId}/type/{type}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<SpotResponse>> getByType(
            @PathVariable Integer lotId,
            @PathVariable String type) {

        return ResponseEntity.ok(
                spotService.getByType(lotId, type));
    }

    /**
     * Occupy Spot
     */
    @PatchMapping("/{spotId}/occupy")
    @PreAuthorize("hasAnyRole('ADMIN','LOT_MANAGER','DRIVER')")
    public ResponseEntity<SpotResponse> occupySpot(
            @PathVariable Integer spotId) {

        return ResponseEntity.ok(
                spotService.occupySpot(spotId));
    }

    /**
     * Release Spot
     */
    @PatchMapping("/{spotId}/release")
    @PreAuthorize("hasAnyRole('ADMIN','LOT_MANAGER','DRIVER')")
    public ResponseEntity<SpotResponse> releaseSpot(
            @PathVariable Integer spotId) {

        return ResponseEntity.ok(
                spotService.releaseSpot(spotId));
    }

    /**
     * Update Spot
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','LOT_MANAGER')")
    public ResponseEntity<SpotResponse> updateSpot(
            @PathVariable Integer id,
            @Valid @RequestBody SpotRequest request) {

        return ResponseEntity.ok(
                spotService.updateSpot(id, request));
    }

}
