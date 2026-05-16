package com.atharva.bookingservice.ServiceImpl;

import com.atharva.bookingservice.DTO.BookingRequest;
import com.atharva.bookingservice.Repository.BookingRepo;
import com.atharva.bookingservice.Repository.PassengerRepo;
import com.atharva.bookingservice.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepo bookingRepo;
    private final PassengerRepo passengerRepo;

    @Override
    public void bookFlight(BookingRequest bookingRequest) {


    }
}
