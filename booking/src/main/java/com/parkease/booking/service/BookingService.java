package com.parkease.booking.service;

import com.parkease.booking.dto.BookingRequest;
import com.parkease.booking.dto.BookingResponse;
import com.parkease.booking.entity.Booking;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    BookingResponse createBooking(
            BookingRequest booking);

    BookingResponse getBookingById(Long bookingId);

    List<BookingResponse> getBookingsByUser(
            Long userId);

    List<BookingResponse> getBookingsByLot(
            Long lotId);

    List<BookingResponse> getActiveBookings();

    void cancelBooking(Long bookingId);

    BookingResponse checkIn(Long bookingId);

    BookingResponse checkOut(Long bookingId);

    BookingResponse extendBooking(
            Long bookingId,
            LocalDateTime endTime);

    Double calculateAmount(
            Long bookingId);
}
