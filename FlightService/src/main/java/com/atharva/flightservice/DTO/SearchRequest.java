package com.atharva.flightservice.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record SearchRequest(
        @NotBlank
        String source,
        @NotBlank
        String destination,
        @NotNull
        LocalDate date,
        @NotNull
        Integer passengers
) {




}
