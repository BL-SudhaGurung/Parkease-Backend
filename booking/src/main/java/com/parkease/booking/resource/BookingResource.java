package com.parkease.booking.resource;

import com.parkease.booking.dto.BookingRequest;
import com.parkease.booking.dto.BookingResponse;
import com.parkease.booking.service.BookingService;
import com.parkease.booking.util.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingResource {


    private final BookingService bookingService;

    public BookingResource(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<ApiResponse<BookingResponse>> createBooking(
            @Valid @RequestBody BookingRequest request) {

        ApiResponse<BookingResponse> apiResponse= new ApiResponse<>();
        apiResponse.setSuccess(true);
        apiResponse.setMessage("Booking is created Successfully");
        apiResponse.setData(bookingService.createBooking(request));
        apiResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponse);
    }

    @GetMapping("/{bookingId}")
    @PreAuthorize("hasAnyRole('DRIVER','LOT_MANAGER','ADMIN')")
    public ResponseEntity<ApiResponse<BookingResponse>> getBooking(@Valid @RequestBody BookingRequest request)
    {

        ApiResponse<BookingResponse> apiResponse= new ApiResponse<>();

//        apiResponse.setSuccess(true);
//        apiResponse.setMessage("Spot added Successfully");
//        apiResponse.setData(spotService.addSpot(request));
//        apiResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponse);
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<ApiResponse<BookingResponse>> getloggedInUser(@Valid @RequestBody BookingRequest request)
    {

        ApiResponse<BookingResponse> apiResponse= new ApiResponse<>();

//        apiResponse.setSuccess(true);
//        apiResponse.setMessage("Spot added Successfully");
//        apiResponse.setData(spotService.addSpot(request));
//        apiResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponse);
    }


    @GetMapping("/active")
    @PreAuthorize("hasRole('DRIVER')")
    public ResponseEntity<ApiResponse<BookingResponse>> activeBooking(@Valid @RequestBody BookingRequest request)
    {

        ApiResponse<BookingResponse> apiResponse= new ApiResponse<>();

//        apiResponse.setSuccess(true);
//        apiResponse.setMessage("Spot added Successfully");
//        apiResponse.setData(spotService.addSpot(request));
//        apiResponse.setTimestamp(LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(apiResponse);
    }
//
//    @GetMapping("/history")
//    @PreAuthorize("hasRole('DRIVER')")
//    public ResponseEntity<ApiResponse<BookingResponse>> activeBooking(@Valid @RequestBody BookingRequest request)
//    {
//
//        ApiResponse<BookingResponse> apiResponse= new ApiResponse<>();
//
////        apiResponse.setSuccess(true);
////        apiResponse.setMessage("Spot added Successfully");
////        apiResponse.setData(spotService.addSpot(request));
////        apiResponse.setTimestamp(LocalDateTime.now());
//
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(apiResponse);
//    }

//
//@GetMapping("/status/{status}")
//@PreAuthorize("hasRole('Admin')")
//public ResponseEntity<ApiResponse<BookingResponse>> getBookingByStatus(@Valid @RequestParam String status)
//{
//
//    ApiResponse<BookingResponse> apiResponse= new ApiResponse<>();
//
////        apiResponse.setSuccess(true);
////        apiResponse.setMessage("Spot added Successfully");
////        apiResponse.setData(spotService.addSpot(request));
////        apiResponse.setTimestamp(LocalDateTime.now());
//
//    return ResponseEntity
//            .status(HttpStatus.CREATED)
//            .body(apiResponse);
//}

//@GetMapping("/lot/{lotId}")
//@PreAuthorize("hasRole('LOT_MANAGER','ADMIN')")
//public ResponseEntity<ApiResponse<BookingResponse>> activeBooking(@Valid @RequestBody BookingRequest request)
//{
//
//    ApiResponse<BookingResponse> apiResponse= new ApiResponse<>();
//
////        apiResponse.setSuccess(true);
////        apiResponse.setMessage("Spot added Successfully");
////        apiResponse.setData(spotService.addSpot(request));
////        apiResponse.setTimestamp(LocalDateTime.now());
//
//    return ResponseEntity
//            .status(HttpStatus.CREATED)
//            .body(apiResponse);
//}

//    @GetMapping("/spot/{spotId}")
//    @PreAuthorize("hasRole('LOT_MANAGER','ADMIN')")
//    public ResponseEntity<ApiResponse<BookingResponse>> activeBooking(@Valid @RequestBody BookingRequest request)
//    {
//
//        ApiResponse<BookingResponse> apiResponse= new ApiResponse<>();
//
////        apiResponse.setSuccess(true);
////        apiResponse.setMessage("Spot added Successfully");
////        apiResponse.setData(spotService.addSpot(request));
////        apiResponse.setTimestamp(LocalDateTime.now());
//
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(apiResponse);
//    }

//    @PutMapping("/{bookingId}/cancel")
//
//
//    @PutMapping("/{bookingId}/check-in")
//    @PutMapping("/{bookingId}/check-out")
//    @PutMapping("/{bookingId}/extend")
//    @DeleteMapping("/{bookingId}")


}
