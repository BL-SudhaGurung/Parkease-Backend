package com.parkease.booking.service;

import com.parkease.booking.entity.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    Booking createBooking(
            Booking booking);

    Booking getBookingById(Long bookingId);

    List<Booking> getBookingsByUser(
            Long userId);

    List<Booking> getBookingsByLot(
            Long lotId);

    List<Booking> getActiveBookings();

    void cancelBooking(Long bookingId);

    Booking checkIn(Long bookingId);

    Booking checkOut(Long bookingId);

    Booking extendBooking(
            Long bookingId,
            LocalDateTime endTime);

    Double calculateAmount(
            Long bookingId);
}
