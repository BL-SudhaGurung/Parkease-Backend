package com.parkease.booking.service;

import com.parkease.booking.client.AuthClient;
import com.parkease.booking.client.ParkingClient;
import com.parkease.booking.client.SpotClient;
import com.parkease.booking.client.VehicleClient;
import com.parkease.booking.dto.*;
import com.parkease.booking.entity.Booking;
import com.parkease.booking.enums.BookingStatus;
import com.parkease.booking.enums.ErrorCode;
import com.parkease.booking.exception.ApiException;
import com.parkease.booking.exception.ResourceException;
import com.parkease.booking.repository.BookingRepo;
import feign.FeignException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImp implements BookingService{

    private final BookingRepo bookingRepo;
    private final AuthClient authClient;
    private final VehicleClient vehicleClient;
    private final ParkingClient parkinglot;
    private final SpotClient spotClient;
    private final ModelMapper modelMapper;

    public BookingServiceImp(BookingRepo bookingRepo, AuthClient user, VehicleClient vehicle, ParkingClient parkinglot, SpotClient spotClient, ModelMapper modelMapper) {
        this.bookingRepo = bookingRepo;
        this.authClient = user;
        this.vehicleClient = vehicle;
        this.parkinglot = parkinglot;
        this.spotClient = spotClient;
        this.modelMapper = modelMapper;
    }


        @Override
        public BookingResponse createBooking(BookingRequest request) {

        try{
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            UserResponseDto user=authClient.getUserByUsername(username);
            if(user==null) throw  new ResourceException(ErrorCode.RESOURCE_NOT_AVALIABLE,"User is not avaliable");


            VehicleResponseDto vehicle =vehicleClient.getVehicle(request.getVehiclePlate());
            if(vehicle==null) throw  new ResourceException(ErrorCode.RESOURCE_NOT_AVALIABLE,"Vehicle is not found");

            ParkingLotResponseDto  parking =parkinglot.getLot(request.getLotId());
            if(parking==null) throw  new ResourceException(ErrorCode.RESOURCE_NOT_AVALIABLE,"parking is not found");

            ParkingSpotResponseDto spot=spotClient.getSpot(request.getSpotId());
            if(spot==null)throw  new ResourceException(ErrorCode.RESOURCE_NOT_AVALIABLE,"spot is not found");



            Booking booking=new Booking();
            booking.setUserId(user.getUserId());
            booking.setVehiclePlate(vehicle.getVehiclePlate());
            booking.setLotId(parking.getLotId());
            booking.setSpotId(spot.getSpotId());
            booking.setStatus(BookingStatus.ACTIVE);
            bookingRepo.save(booking);
            return modelMapper.map(booking,BookingResponse.class);
        } catch (FeignException.NotFound eX) {
            throw new ResourceException(ErrorCode.RESOURCE_NOT_AVALIABLE, "Resource not found: " + eX.getMessage());
        }

        }

    @Override
    public BookingResponse getBookingById(Long bookingId) {
        return null;
    }

    @Override
    public List<BookingResponse> getBookingsByUser(Long userId) {
        return List.of();
    }

    @Override
    public List<BookingResponse> getBookingsByLot(Long lotId) {
        return List.of();
    }

    @Override
    public List<BookingResponse> getActiveBookings() {
        return List.of();
    }

    @Override
    public void cancelBooking(Long bookingId) {

    }

    @Override
    public BookingResponse checkIn(Long bookingId) {
        return null;
    }

    @Override
    public BookingResponse checkOut(Long bookingId) {
        return null;
    }

    @Override
    public BookingResponse extendBooking(Long bookingId, LocalDateTime endTime) {
        return null;
    }

    @Override
    public Double calculateAmount(Long bookingId) {
        return 0.0;
    }
}
