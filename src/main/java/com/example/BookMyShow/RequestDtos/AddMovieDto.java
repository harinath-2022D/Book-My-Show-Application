package com.example.BookMyShow.RequestDtos;

import com.example.BookMyShow.Enums.Genre;
import com.example.BookMyShow.Enums.Language;
import lombok.Data;

import java.util.Date;
@Data
public class AddMovieDto {

    private String name;
    private int duration;
    private double rating;
    private Date releaseDate;

    private Genre genre;
    private Language language;
}
