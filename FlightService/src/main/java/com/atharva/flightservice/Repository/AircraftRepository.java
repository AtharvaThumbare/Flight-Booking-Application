package com.atharva.flightservice.Repository;

import com.atharva.flightservice.Entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
}
