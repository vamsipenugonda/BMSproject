package com.example.bookmyshow.Services;

import com.example.bookmyshow.models.*;
import com.example.bookmyshow.repositeries.BookingRepository;
import com.example.bookmyshow.repositeries.ShowRepository;
import com.example.bookmyshow.repositeries.ShowSeatRepository;
import com.example.bookmyshow.repositeries.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRepository bookingRepository;
    private UserRepository userRepository;

    @Autowired
    BookingService(
            UserRepository userRepository,
            ShowRepository showRepository,
            ShowSeatRepository showSeatRepository,
            BookingRepository bookingRepository
    ){
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
    }
    //@Transactional(isolation = Isolation.SERIALIZABLE)

    public Booking bookTicket(
            List<Integer> showSeatIds,
            int showId,
            int userId
    ){
        // 1. Get the User from userId
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException("user not found");
        }
        User user = userOptional.get();
        // 2. Get the Show from showId
        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new RuntimeException("show not found");
        }
        Show show = showOptional.get();
        // ------- Transaction start -------
        // 3. Get the ShowSeat from Ids
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        // 4. Check if all the seats are available
        for(ShowSeat showSeat : showSeats){
            if(!showSeat.getShowSeatStatus().equals(ShowSeatStatus.EMPTY)){
                throw new RuntimeException("show seat not empty");
            }
        }

        // 5. If No, throw error return the response
        // 6. If yes, make the seats as blocked
        List<ShowSeat> showSeatList = new ArrayList<>();
        for(ShowSeat showSeat : showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeat.setBlockedAt(new Date());
            // 7. Save the updates data of ShowSeats in the db
            showSeatList.add(showSeatRepository.save(showSeat));
        }

        // -- Transaction stops
        // 8. Create a corresponding booking object
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setShowSeats(showSeatList);
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setBookedAt(new Date());
        booking.setAmount(100);
        booking.setPayments(new ArrayList<>());
        bookingRepository.save(booking);
        // 9. return the booking object
        // Break till 10:25 pm

        return booking;
    }
}


// PriceCalculatorService
// calculatePrice
// Show Seats
// for all the show seats

// 1. find showSeatType using the show
// 2. for all the showSeat you will check the type and price


