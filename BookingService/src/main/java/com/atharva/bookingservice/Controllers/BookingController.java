package com.atharva.bookingservice.Controllers;


import com.atharva.bookingservice.DTO.BookingRequest;
import com.atharva.bookingservice.DTO.PassengerRequest;
import com.atharva.bookingservice.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

      private final BookingService bookingService;

        @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest bookingRequest) {

                 bookingService.bookFlight(bookingRequest);


                return ResponseEntity.ok("Booking created successfully");
        }

}
