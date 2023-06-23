package com.example.BookMyShow.Transformers;

import com.example.BookMyShow.Models.Movie;
import com.example.BookMyShow.RequestDtos.AddMovieDto;

public class MovieTransformer {

    public static Movie convertMovieDtoToEntity(AddMovieDto addMovieDto){

        Movie movie = Movie.builder()
                .name(addMovieDto.getName())
                .duration(addMovieDto.getDuration())
                .genre(addMovieDto.getGenre())
                .rating(addMovieDto.getRating())
                .releaseDate(addMovieDto.getReleaseDate())
                .language(addMovieDto.getLanguage())
                .build();

        return movie;
    }
}
