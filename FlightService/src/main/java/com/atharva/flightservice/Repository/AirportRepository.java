package com.atharva.flightservice.Repository;

import com.atharva.flightservice.Entity.Airports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airports, Long> {
}
