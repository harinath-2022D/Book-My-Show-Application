package com.example.BookMyShow.Services;

import com.example.BookMyShow.Enums.SeatType;
import com.example.BookMyShow.Models.Theater;
import com.example.BookMyShow.Models.TheaterSeat;
import com.example.BookMyShow.Repositories.TheaterRepository;
import com.example.BookMyShow.RequestDtos.AddTheaterDto;
import com.example.BookMyShow.RequestDtos.AddTheaterSeatDto;
import com.example.BookMyShow.Transformers.ThaterTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;

    public String addTheater(AddTheaterDto addTheaterDto){

        Theater theater = ThaterTransformer.convertDtoToEntity(addTheaterDto);

        theaterRepository.save(theater);

        return "Theater added successfully";
    }

    public String addTheaterSeats(AddTheaterSeatDto theaterSeatDto) {

        int columns = theaterSeatDto.getNumberOfSeatsInARow();
        int numberOfBasicSeats = theaterSeatDto.getNumberOfBasicSeats();
        int numberOfPremiumSeats = theaterSeatDto.getNumberOfPremiumSeats();

        Theater theater = theaterRepository.findByLocation(theaterSeatDto.getLocation());

        List<TheaterSeat> theaterSeatList = theater.getTheaterSeatList();

        // assigning SeatNo
        int counter = 1;
        char ch = 'A';

        for(int i=1; i<=numberOfBasicSeats; i++){

            String seatNo = counter+"";
            seatNo = seatNo + ch;
            ch++;

            if(ch-'A' == columns){
                ch = 'A';
                counter++;
            }

            TheaterSeat theaterSeat = new TheaterSeat();

            theaterSeat.setTheater(theater);
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.BASIC);

            theaterSeatList.add(theaterSeat); // storing child entity in parent entity
        }

        for(int i=1; i<=numberOfPremiumSeats; i++){

            String seatNo = counter+"";
            seatNo = seatNo + ch;
            ch++;

            if(ch-'A' == columns){
                ch = 'A';
                counter++;
            }

            TheaterSeat theaterSeat = new TheaterSeat();

            theaterSeat.setTheater(theater);
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.PREMIUM);

            theaterSeatList.add(theaterSeat); // storing child entity in parent entity
        }

        theaterRepository.save(theater); // bi directional mapping

        return "Theater Seats add Successfully to "+theater.getName();
    }
}
