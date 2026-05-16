package com.atharva.flightservice.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID routeUUId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_airports_id")
    private Airports source;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
    private Airports destination;

    @Column(nullable = false)
    private long distance;


    @PrePersist
    public void prePersist() {
        this.routeUUId = UUID.randomUUID();

    }
}
