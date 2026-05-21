package com.atharva.bookingservice.ServiceImpl;

import com.atharva.bookingservice.Client.FlightClient;
import com.atharva.bookingservice.DTO.BookingRequest;
import com.atharva.bookingservice.DTO.ReserveSeatsRequest;
import com.atharva.bookingservice.DTO.ReserveSeatsResponse;
import com.atharva.bookingservice.Entity.Booking;
import com.atharva.bookingservice.Entity.Passenger;
import com.atharva.bookingservice.Exceptions.BookingNotFoundException;
import com.atharva.bookingservice.Repository.BookingRepo;
import com.atharva.bookingservice.Repository.PassengerRepo;
import com.atharva.bookingservice.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepo bookingRepo;
    private final PassengerRepo passengerRepo;
    private final FlightClient flightClient;

    @Override
    @Transactional
    public void bookFlight(UUID userUUID, UUID flightUUID, BookingRequest bookingRequest) {

        ReserveSeatsRequest reserveSeatsRequest = new ReserveSeatsRequest(bookingRequest.passengers().size());

         ReserveSeatsResponse reserveSeatsResponse =flightClient.reserveSeats(flightUUID,reserveSeatsRequest);





       Booking booking = Booking.builder().userUUId(userUUID).flightUUId(flightUUID)
               .totalPassengers(bookingRequest.passengers().size())
               .totalPrice(reserveSeatsResponse.totalPrice()).status(Booking.BookingStatus.PENDING).build();

        List<Passenger> passengerList=  bookingRequest.passengers()
                .stream()
                .map(passengerRequest ->

                        Passenger.builder()

                                .firstName(
                                        passengerRequest.firstName()
                                )

                                .lastName(
                                        passengerRequest.lastName()
                                )

                                .age(
                                        passengerRequest.age()
                                )

                                .gender(passengerRequest.gender())

                                .booking(booking)

                                .build()
                )
                .toList();

              booking.setPassengers(passengerList);

              bookingRepo.save(booking);





    }

    @Override
    public void cancelFLight(UUID bookingUUID) {
            Booking booking = bookingRepo.findBookingByBookingUUId(bookingUUID)
                    .orElseThrow(() -> new BookingNotFoundException("Booking not found for user and flight"));

            ReserveSeatsRequest reserveSeatsRequest = new ReserveSeatsRequest(booking.getTotalPassengers());

            flightClient.releaseSeats(booking.getFlightUUId(),reserveSeatsRequest);

            booking.setStatus(Booking.BookingStatus.CANCELLED);

            bookingRepo.save(booking);
    }
}
