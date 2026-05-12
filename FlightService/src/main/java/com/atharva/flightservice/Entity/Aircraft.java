package com.atharva.flightservice.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airlines_id",nullable = false)
    private Airline airline;

    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private Integer totalSeats;
}
