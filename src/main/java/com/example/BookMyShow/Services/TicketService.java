package com.example.BookMyShow.Services;

import com.example.BookMyShow.Exceptions.InValidSeatException;
import com.example.BookMyShow.Exceptions.ShowNotFound;
import com.example.BookMyShow.Models.Show;
import com.example.BookMyShow.Models.ShowSeat;
import com.example.BookMyShow.Models.Ticket;
import com.example.BookMyShow.Models.User;
import com.example.BookMyShow.Repositories.ShowRepository;
import com.example.BookMyShow.Repositories.TicketRepository;
import com.example.BookMyShow.Repositories.UserRepository;
import com.example.BookMyShow.RequestDtos.TicketReqDto;
import com.example.BookMyShow.ResponseDtos.TicketResponseDto;
import com.example.BookMyShow.Transformers.TicketTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShowRepository showRepository;

    public TicketResponseDto bookTicket(TicketReqDto ticketReqDto) throws ShowNotFound, InValidSeatException{

        int userId = ticketReqDto.getUserId();

        Optional<User> userOptional = userRepository.findById(userId);

        User user = userOptional.get();

        int showId = ticketReqDto.getShowId();

        Optional<Show> showOptional = showRepository.findById(showId);

        if(!showOptional.isPresent()){
            throw  new ShowNotFound("show is not available");
        }

        Show show = showOptional.get();
        List<String> reqSeats = ticketReqDto.getReqSeats();

        int price = validateReqSeatsAndCalculatePrice(show, reqSeats);

        if(price == -1){
            throw new InValidSeatException("invalid seats");
        }

        Ticket ticket = new Ticket();

        String bookedSeats = TicketTransformer.convertListToString(ticketReqDto.getReqSeats());

        ticket.setBookedSeats(bookedSeats);
        ticket.setTotalPrice(price);
        ticket.setUser(user);
        ticket.setShow(show);

        ticket = ticketRepository.save(ticket);

        user.getTicketList().add(ticket);
        show.getTicketList().add(ticket);

        userRepository.save(user);
        showRepository.save(show);

        return TicketTransformer.convertEntityToDto(show,ticket);

    }

    private int validateReqSeatsAndCalculatePrice(Show show, List<String> reqSeats){

        int price = 0;

        List<ShowSeat> showSeatList = show.getShowSeatList();

        for(ShowSeat showSeat : showSeatList){

            String seatNo = showSeat.getSeatNo();

            if(reqSeats.contains(seatNo)){
                if(showSeat.isAvailable() == false){
                    return -1;
                }else{
                    price += showSeat.getPrice();
                    showSeat.setAvailable(false);
                }
            }
        }
        return price;
    }
}
