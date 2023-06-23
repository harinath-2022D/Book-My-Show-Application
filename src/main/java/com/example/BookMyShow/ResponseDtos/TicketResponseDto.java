package com.example.BookMyShow.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketResponseDto {
    private LocalTime showTime;
    private Date showDate;
    private String movie;
    private String theater;
    private String bookedSeats;
    private String location;

    private String status;
    private String message;
}
