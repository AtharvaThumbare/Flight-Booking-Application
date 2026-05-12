package com.atharva.flightservice.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "airlines")

public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)

    private String code;
    @Column(nullable = false)
    private String name;


}
