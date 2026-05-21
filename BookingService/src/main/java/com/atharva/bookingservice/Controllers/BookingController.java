package com.atharva.bookingservice.Controllers;


import com.atharva.bookingservice.DTO.BookingRequest;
import com.atharva.bookingservice.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bookings")
public class BookingController {

      private final BookingService bookingService;

        @PostMapping
    public ResponseEntity<?> createBooking(@RequestHeader("X-User-Id") UUID userUUID ,@PathVariable UUID filghtUUID, @RequestBody BookingRequest bookingRequest) {

                 bookingService.bookFlight(userUUID, filghtUUID, bookingRequest);


                return ResponseEntity.ok("Booking created successfully");
        }


         @PostMapping
        public ResponseEntity<?> cancelBooking(@PathVariable UUID bookingUUID) {

             bookingService.cancelFLight(bookingUUID);
             return ResponseEntity.ok("Booking cancelled successfully");
         }

}
