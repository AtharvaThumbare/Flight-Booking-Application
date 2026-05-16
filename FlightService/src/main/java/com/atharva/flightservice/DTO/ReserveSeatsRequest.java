package com.atharva.flightservice.DTO;

import jakarta.validation.constraints.Positive;

public record ReserveSeatsRequest(
        @Positive
        Integer  passengers

) {
}
