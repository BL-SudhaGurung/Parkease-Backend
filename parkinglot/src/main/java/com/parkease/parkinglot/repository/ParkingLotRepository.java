package com.parkease.parkinglot.repository;


import com.parkease.parkinglot.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotRepository extends JpaRepository<ParkingLot,Integer> {


}
