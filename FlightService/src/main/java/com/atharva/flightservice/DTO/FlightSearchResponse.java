package com.atharva.flightservice.DTO;

import java.time.LocalDateTime;

public record FlightSearchResponse(
        Long id,
        String flightNumber,
        String airlineName,

        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        String basePrice

) {
}
