package com.example.bookmyshow.repositeries;

import com.example.bookmyshow.models.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends CrudRepository<Booking,Integer> {
    @Override
    Booking save(Booking entity);
}
