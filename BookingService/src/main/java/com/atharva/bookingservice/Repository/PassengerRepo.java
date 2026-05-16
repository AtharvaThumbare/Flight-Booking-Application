package com.atharva.bookingservice.Repository;

import com.atharva.bookingservice.Entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepo extends JpaRepository<Passenger, Long> {


}
