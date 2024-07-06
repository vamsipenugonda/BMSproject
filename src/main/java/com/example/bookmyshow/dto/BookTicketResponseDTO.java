package com.example.bookmyshow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookTicketResponseDTO {
    private ResponseStatus responseStatus;
    private Long bookingId;
    private int amount;
}
