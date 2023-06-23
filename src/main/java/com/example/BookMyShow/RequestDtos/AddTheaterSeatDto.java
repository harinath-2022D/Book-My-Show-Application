package com.example.BookMyShow.RequestDtos;

import lombok.Data;

@Data
public class AddTheaterSeatDto {
    private int numberOfSeatsInARow;
    private int numberOfBasicSeats;
    private int numberOfPremiumSeats;
    private String location;
}
