package com.atharva.flightservice.Entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "airlines")

public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column( updatable = false, nullable = false, unique = true)
    private UUID airlineUUId;

    @Column(nullable = false, unique = true)

    private String code;
    @Column(nullable = false)
    private String name;

    @PrePersist
    protected void prePersist() {
        if (this.airlineUUId == null) {
            this.airlineUUId = UUID.randomUUID();

        }


   }


}
