package com.atharva.flightservice.Entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="routes")
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
    protected void prePersist() {
        if (this.routeUUId == null) {
            this.routeUUId = UUID.randomUUID();

        }
    }



}
