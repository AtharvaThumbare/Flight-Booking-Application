package com.atharva.flightservice.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightSchedules {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String flight_number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name= "airlines_id")
    private Airline airline;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name="aircraft_id")
    private Aircraft aircraft;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false,name="route_id")
    private Route route;

    @Column(nullable = false)
    private LocalDateTime departure_time;

    @Column(nullable = false)
    private LocalDateTime arrival_time;

    @Column(nullable = false)
    private Integer available_seats;

    @Column(nullable = false)
    private BigDecimal basePrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlightStatus status;



    public enum FlightStatus {
        SCHEDULED,
        DELAYED,
        CANCELLED,
        BOARDING,
        DEPARTED,
        ARRIVED
    }


}
