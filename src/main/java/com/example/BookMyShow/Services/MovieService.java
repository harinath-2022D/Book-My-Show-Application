package com.example.BookMyShow.Services;

import com.example.BookMyShow.Models.Movie;
import com.example.BookMyShow.Repositories.MovieRepository;
import com.example.BookMyShow.RequestDtos.AddMovieDto;
import com.example.BookMyShow.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public String addMovie(AddMovieDto addMovieDto){

        Movie movie = MovieTransformer.convertMovieDtoToEntity(addMovieDto);

        movieRepository.save(movie);

        return movie.getName()+" movie added Successfully.";
    }
}
