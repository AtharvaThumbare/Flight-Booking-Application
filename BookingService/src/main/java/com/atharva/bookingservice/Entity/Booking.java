package com.atharva.bookingservice.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID bookingUUId;

    private UUID userUUId;

    private UUID flightUUId;

    private Integer totalPassengers;

    private BigDecimal totalPrice;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @OneToMany(
            mappedBy = "booking",
            cascade = CascadeType.ALL
    )
    private List<Passenger> passengers;







    public enum BookingStatus {

        PENDING,

        CONFIRMED,

        CANCELLED,

        FAILED
    }


    public void prePersist() {

        this.bookingUUId = UUID.randomUUID();
    }


}
