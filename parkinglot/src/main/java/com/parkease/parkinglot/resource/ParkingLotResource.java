package com.parkease.parkinglot.resource;

import com.parkease.parkinglot.entity.ParkingLot;
import com.parkease.parkinglot.service.ParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping
public class ParkingLotResource {

    private final ParkingLotService parkingLotService;

    public ParkingLotResource(ParkingLotService parkingLotService) {
        this.parkingLotService = parkingLotService;
    }


    @PostMapping
    public ResponseEntity<ParkingLot> createLot(
            @RequestBody ParkingLot parkingLot) {

        return ResponseEntity.ok(
                parkingLotService.createLot(parkingLot)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingLot> getLotById(
            @PathVariable Long id) {

        return parkingLotService.getLotById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<ParkingLot>> getByCity(
            @PathVariable String city) {

        return ResponseEntity.ok(
                parkingLotService.getLotsByCity(city)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ParkingLot> update(
            @PathVariable Long id,
            @RequestBody ParkingLot parkingLot) {

        return ResponseEntity.ok(
                parkingLotService.updateLot(id, parkingLot)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        parkingLotService.deleteLot(id);

        return ResponseEntity.noContent().build();
    }
}
