package com.atharva.flightservice.Repository;

import com.atharva.flightservice.Entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
}
