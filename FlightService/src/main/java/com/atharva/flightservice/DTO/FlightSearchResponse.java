package com.atharva.flightservice.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

public record FlightSearchResponse(
        UUID flightUUID,
        String flightNumber,
        String airlineName,

        LocalDateTime departureTime,
        LocalDateTime arrivalTime,
        String basePrice

) {
}
