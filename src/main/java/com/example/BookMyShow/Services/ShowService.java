package com.example.BookMyShow.Services;

import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Exceptions.NoMovieFoundException;
import com.example.BookMyShow.Exceptions.NoTheaterFoundException;
import com.example.BookMyShow.Exceptions.ShowNotFound;
import com.example.BookMyShow.Models.*;
import com.example.BookMyShow.Repositories.MovieRepository;
import com.example.BookMyShow.Repositories.ShowRepository;
import com.example.BookMyShow.Repositories.TheaterRepository;
import com.example.BookMyShow.RequestDtos.AddShowDto;
import com.example.BookMyShow.RequestDtos.AddShowSeatsDto;
import com.example.BookMyShow.Transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;

    public String addShow(AddShowDto addShowDto) throws NoMovieFoundException, NoTheaterFoundException {

        Show show = ShowTransformer.convertDtoToEntity(addShowDto);

        Optional<Movie> movieOptional = movieRepository.findById(addShowDto.getMovieId());

        if(!movieOptional.isPresent()){
            throw new NoMovieFoundException("Movie Not Found");
        }

        Optional<Theater> theaterOptional = theaterRepository.findById(addShowDto.getTheaterId());

        if(!theaterOptional.isPresent()){
            throw new NoTheaterFoundException("THeater Not Found");
        }

        Movie movie =  movieOptional.get();
        Theater theater = theaterOptional.get();

        show.setMovie(movie);
        show.setTheater(theater);

        show = showRepository.save(show);

        movie.getShowList().add(show);
        theater.getShowList().add(show);

        movieRepository.save(movie);
        theaterRepository.save(theater);

        return "Show added successfully";
    }


    public String associateSeats(AddShowSeatsDto addShowSeatsDto)throws ShowNotFound{

        Optional<Show> showOptional = showRepository.findById(addShowSeatsDto.getShowId());

        if(!showOptional.isPresent()){
            throw  new ShowNotFound("show id incorrect");
        }

        Show show = showOptional.get();

        Theater theater = show.getTheater();

        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(TheaterSeat theaterSeat : theaterSeatList){

            ShowSeat showSeat = new ShowSeat();

            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());

            if(showSeat.getSeatType().equals(SeatType.BASIC)){
                showSeat.setPrice(addShowSeatsDto.getPriceForBasicSeat());
                showSeat.setFoodAttached(false);
            }else{
                showSeat.setPrice(addShowSeatsDto.getPriceForPremiumSeat());
                showSeat.setFoodAttached(true);
            }

            showSeat.setAvailable(true);

            showSeat.setShow(show);
            //show.getShowSeatList().add(showSeat);

            showSeatList.add(showSeat);

        }

        showRepository.save(show);

        return "show seats are associated";
    }
}
