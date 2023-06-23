package com.example.BookMyShow.RequestDtos;

import lombok.Data;

import java.util.List;

@Data
public class TicketReqDto {
    private int showId;
    private int userId;
    private List<String> reqSeats;
}
