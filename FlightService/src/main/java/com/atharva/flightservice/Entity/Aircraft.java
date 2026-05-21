package com.atharva.flightservice.Entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "aircrafts")
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false, unique = true)
    private UUID aircraftUUId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airlines_id", nullable = false)
    private Airline airline;

    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private Integer totalSeats;


    @PrePersist
    protected void onCreate() {
        if (this.aircraftUUId == null) {
            this.aircraftUUId = UUID.randomUUID();
        }
    }
}
