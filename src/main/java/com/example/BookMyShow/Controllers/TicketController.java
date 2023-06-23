package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.RequestDtos.TicketReqDto;
import com.example.BookMyShow.ResponseDtos.TicketResponseDto;
import com.example.BookMyShow.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
