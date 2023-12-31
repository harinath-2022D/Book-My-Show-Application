package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.Models.Ticket;
import com.example.BookMyShow.RequestDtos.TicketReqDto;
import com.example.BookMyShow.ResponseDtos.TicketResponseDto;
import com.example.BookMyShow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @PostMapping("/book-ticket")
    public TicketResponseDto bookTicket(@RequestBody TicketReqDto ticketReqDto){
        try{
            TicketResponseDto ticketResponseDto = ticketService.bookTicket(ticketReqDto);
            ticketResponseDto.setStatus("SUCCESS");
            ticketResponseDto.setMessage("please visit on time.");

            return ticketResponseDto;

        }catch (Exception e){
            TicketResponseDto ticketResponseDto = new TicketResponseDto();
            ticketResponseDto.setStatus("FAILURE");
            ticketResponseDto.setMessage("Reason : Please select available tickets.");

            return ticketResponseDto;
        }
    }

    @PostMapping("/cancelticket")
    public String cancelTicket(@RequestParam("ticketid") int id){
        try {
            return ticketService.cancelTicket(id);
        }catch (Exception e){
            return e.getMessage();
        }
    }


}
