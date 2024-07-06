package com.example.bookmyshow.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Seat extends BaseModel{
    private String number;
    private int colVal;
    private  int rowVal;
    @ManyToOne
    private SeatType seatType;

}
