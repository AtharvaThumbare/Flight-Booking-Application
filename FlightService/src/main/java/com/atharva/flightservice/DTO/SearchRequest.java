package com.atharva.flightservice.DTO;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record SearchRequest(
        @NotBlank
        String source,
        @NotBlank
        String destination,
        @NotBlank
        LocalDate date,
        @NotBlank
        Integer passengers
) {




}
