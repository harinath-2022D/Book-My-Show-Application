package com.example.BookMyShow.Transformers;


import com.example.BookMyShow.Models.Show;
import com.example.BookMyShow.Models.Ticket;
import com.example.BookMyShow.ResponseDtos.TicketResponseDto;

import java.util.List;

public class TicketTransformer {

    public static String convertListToString(List<String> list){

        StringBuilder sb = new StringBuilder();

        int size = list.size()-1;

        for(String str : list){

            sb.append(str);
            if(str.equals(list.get(size))) break;;
            sb.append(',');

        }

        return String.valueOf(sb);
    }

    public static TicketResponseDto convertEntityToDto(Show show, Ticket ticket){

        TicketResponseDto ticketResponseDto = TicketResponseDto.builder()
                .bookedSeats(ticket.getBookedSeats())
                .movie(show.getMovie().getName())
                .theater(show.getTheater().getName())
                .showDate(show.getDate())
                .showTime(show.getTime())
                .location(show.getTheater().getLocation())
                .build();

        return ticketResponseDto;
    }
}
