package com.example.BookMyShow.RequestDtos;

import com.example.BookMyShow.Models.Movie;
import com.example.BookMyShow.Models.Theater;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class AddShowDto {
    private LocalTime showStartTime;
    private Date showDate;

    private int theaterId;
    private int movieId;
}
