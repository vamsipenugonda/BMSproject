package com.example.bookmyshow.repositeries;

import com.example.bookmyshow.models.Show;
import com.example.bookmyshow.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;
@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType,Integer> {
    @Override
    List<ShowSeatType> findAllById(Iterable<Integer> integers);
    List<ShowSeatType> findByShow(Show show);
}