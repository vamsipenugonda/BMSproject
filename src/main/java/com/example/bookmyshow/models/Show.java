package com.example.bookmyshow.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "shows")
public class Show extends BaseModel{
    @ManyToOne
    private Movie movie;
    private Date startTime;
    //@ManyToOne
   // private  Screen screen;
    private int duration ;
    @Enumerated(EnumType.ORDINAL)
    @ElementCollection
    private List<Features> features;
}
