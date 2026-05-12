package com.atharva.flightservice.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreateFlightRequest(

        @NotBlank
        String flightNumber,

        @NotNull
        Long airlineId,

        @NotNull
        Long aircraftId,

        @NotNull
        Long routeId,

        @NotNull
        LocalDateTime departureTime,

        @NotNull
        LocalDateTime arrivalTime,

        @NotNull
        @Positive
        Integer availableSeats,

        @NotNull
        @Positive
        BigDecimal basePrice

) {
}
