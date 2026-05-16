package com.atharva.flightservice.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID aircraftUUId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airlines_id",nullable = false)
    private Airline airline;

    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private Integer totalSeats;

    @PrePersist
    public void prePersist() {
        this.aircraftUUId = UUID.randomUUID();

    }
}
