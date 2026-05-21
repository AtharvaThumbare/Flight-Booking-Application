package com.atharva.bookingservice.Repository;

import com.atharva.bookingservice.Entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookingRepo extends JpaRepository<Booking, Long> {


      Optional<Booking> findBookingByBookingUUId(UUID bookingUUId);
}
