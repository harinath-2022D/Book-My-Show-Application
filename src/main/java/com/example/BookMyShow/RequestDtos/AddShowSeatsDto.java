package com.example.BookMyShow.RequestDtos;

import lombok.Data;

@Data
public class AddShowSeatsDto {

    private int showId;

    private int priceForBasicSeat;

    private int priceForPremiumSeat;

}
