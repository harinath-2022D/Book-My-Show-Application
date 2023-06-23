package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.RequestDtos.AddShowDto;
import com.example.BookMyShow.RequestDtos.AddShowSeatsDto;
import com.example.BookMyShow.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/addShow")
    public String addShow(@RequestBody AddShowDto addShowDto){
        try {
            return showService.addShow(addShowDto);
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @PostMapping("/associate-seats")
    public String associateSeats(@RequestBody AddShowSeatsDto addShowSeatsDto){
        try{
            return showService.associateSeats(addShowSeatsDto);
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
