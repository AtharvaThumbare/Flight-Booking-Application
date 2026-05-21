package com.atharva.flightservice.DTO;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateFlightRequest(

//        @NotBlank
//        String flightNumber,

        @NotNull
        UUID airlineUUId,

        @NotNull
        UUID aircraftUUId,

        @NotNull
        UUID routeUUId,

        @NotNull
        LocalDateTime departureTime,

        @NotNull
        LocalDateTime arrivalTime,

//        @NotNull
//        @Positive
//        Integer availableSeats,

        @NotNull
        @Positive
        BigDecimal basePrice

) {
}
