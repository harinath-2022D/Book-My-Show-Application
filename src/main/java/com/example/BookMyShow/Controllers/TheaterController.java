package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.RequestDtos.AddTheaterDto;
import com.example.BookMyShow.RequestDtos.AddTheaterSeatDto;
import com.example.BookMyShow.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {

    @Autowired
    TheaterService theaterService;

    @PostMapping("/add")
    public String addTheater(@RequestBody AddTheaterDto addTheaterDto){

        return theaterService.addTheater(addTheaterDto);

    }

    @PostMapping("/add-theaterSeats")
    public String addTheaterSeats(@RequestBody AddTheaterSeatDto theaterSeatDto){

        return theaterService.addTheaterSeats(theaterSeatDto);
    }
}
