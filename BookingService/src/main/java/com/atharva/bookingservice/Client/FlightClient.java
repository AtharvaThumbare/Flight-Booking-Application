package com.atharva.bookingservice.Client;



import com.atharva.bookingservice.DTO.ReserveSeatsRequest;
import com.atharva.bookingservice.DTO.ReserveSeatsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "FLIGHT-SERVICE")
public interface FlightClient {


    @PostMapping("/api/flights/{uuid}/reserve")
    ReserveSeatsResponse reserveSeats(

            @PathVariable UUID uuid,

            @RequestBody ReserveSeatsRequest   request
    );


    @PostMapping("/api/flights/{uuid}/release")
      void releaseSeats(
            @PathVariable UUID flightUUID,

            @RequestBody ReserveSeatsRequest   request
    );




}
