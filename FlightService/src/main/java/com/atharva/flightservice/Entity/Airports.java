package com.atharva.flightservice.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Airports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private UUID airportUUId;
    @Column(nullable = false, unique = true, length = 10)
    private String code;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private String timezone;

    @PrePersist
    public void prePersist() {
        this.airportUUId = UUID.randomUUID();

    }



}
