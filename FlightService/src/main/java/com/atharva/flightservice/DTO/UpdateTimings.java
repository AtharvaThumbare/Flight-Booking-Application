package com.atharva.flightservice.DTO;

import java.time.LocalDateTime;

public record UpdateTimings(


        LocalDateTime departure_time,
        LocalDateTime arrival_time
) {


}
