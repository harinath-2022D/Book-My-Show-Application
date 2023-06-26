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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

//    @Autowired
//    private JavaMailSender emailSender;

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


//
//                String body = "Hi "+user.getName()+" !!!," +"\n"+
//                "your tickets are booked at "+ticket.getBookedAt()+"\n"+
//                "Enjoy your show !!!  ThankYou.";
//
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//
//        mailMessage.setFrom("hari.receivng@gmail.com");
//        mailMessage.setTo(user.getEmailId());
//        mailMessage.setSubject("Ticket Confirmation");
//        mailMessage.setText(body);
//
//        emailSender.send(mailMessage);

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

    public int calculateRefundAmount(List<ShowSeat> showSeatList,List<String> bookedSeatNo) throws InValidSeatException{
        int price = 0;

        for(ShowSeat showSeat : showSeatList){

            String seatNo = showSeat.getSeatNo();

            if(bookedSeatNo.contains(seatNo)){
                if(showSeat.isAvailable() == true){
                    throw new InValidSeatException("incorrect seat numbers");
                }else{
                    price +=showSeat.getPrice();
                    showSeat.setAvailable(true);
                }
            }
        }

        int charges = bookedSeatNo.size() * 20;

        return price - charges;
    }
    public String cancelTicket(int ticketId) throws InValidSeatException{

        Ticket ticket = ticketRepository.findById(ticketId).get();
        Show show = ticket.getShow();
        User user = ticket.getUser();
        List<ShowSeat> showSeatList = show.getShowSeatList();
        String bookedSeatNo = ticket.getBookedSeats();

        String[] seats = bookedSeatNo.split(",");
        List<String> seatList = new ArrayList<>(List.of(seats));

        int price = calculateRefundAmount(showSeatList,seatList);

        List<Ticket> ticketList = user.getTicketList();
        ticketList.remove(ticket);

        show.getTicketList().remove(ticket);

        ticketRepository.deleteById(ticketId);

        userRepository.save(user);
        showRepository.save(show);


        return "ticket cancelled refund amount is -> "+price;
    }
}
