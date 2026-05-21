package com.atharva.flightservice.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="flight_schedules")
public class FlightSchedules {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private UUID flightUUID;

    @Column(nullable = false)
    private String flightNumber;

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
    private LocalDateTime departureTime;

    @Column(nullable = false)
    private LocalDateTime arrivalTime;

    @Column(nullable = false)
    private Integer availableSeats;

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

     @PrePersist
    protected void prePersist() {
        if (this.flightUUID == null) {
            this.flightUUID = UUID.randomUUID();
        }
    }



}
