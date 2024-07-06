package com.example.bookmyshow.controller;

import com.example.bookmyshow.Services.BookingService;
import com.example.bookmyshow.dto.BookTicketRequestDTO;
import com.example.bookmyshow.dto.BookTicketResponseDTO;
import com.example.bookmyshow.dto.ResponseStatus;
import com.example.bookmyshow.models.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;

    @Autowired
    BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    public BookTicketResponseDTO bookTicket(BookTicketRequestDTO request){
        BookTicketResponseDTO response = new BookTicketResponseDTO();
        try {
            Booking booking = bookingService.bookTicket(
                    request.getShowSeatIds(),
                    request.getShowId(),
                    request.getUserId()
            );
            response.setBookingId((long) booking.getId());
            response.setAmount(booking.getAmount());
            response.setResponseStatus(ResponseStatus.SUCCESS);
        } catch(Exception ex){
            response.setResponseStatus(ResponseStatus.FAILURE);
        }
        return response;
    }
}
//check