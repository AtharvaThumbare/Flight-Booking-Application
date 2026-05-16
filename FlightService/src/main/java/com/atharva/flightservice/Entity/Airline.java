package com.atharva.flightservice.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "airlines")

public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID airlineUUId;

    @Column(nullable = false, unique = true)

    private String code;
    @Column(nullable = false)
    private String name;

    @PrePersist
    public void prePersist() {
        this.airlineUUId = UUID.randomUUID();

    }


}
