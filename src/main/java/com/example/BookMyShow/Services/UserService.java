package com.example.BookMyShow.Services;

import com.example.BookMyShow.Exceptions.NoUserFoundException;
import com.example.BookMyShow.Models.Ticket;
import com.example.BookMyShow.Models.User;
import com.example.BookMyShow.Repositories.UserRepository;
import com.example.BookMyShow.RequestDtos.AddUserDto;
import com.example.BookMyShow.ResponseDtos.TicketResponseDto;
import com.example.BookMyShow.ResponseDtos.UserResponseDto;
import com.example.BookMyShow.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String addUser(AddUserDto addUserDto) {

        User user = UserTransformer.convertDtoTOEntity(addUserDto);
        userRepository.save(user);

        return "User has been added successfully";
    }

    public UserResponseDto getOldestUser()throws NoUserFoundException {
        List<User> userList = userRepository.findAll();
        User user = null;
        int maxAge = 0;
        for(User u : userList){
            if(u.getAge()>maxAge){
                maxAge = u.getAge();
                user = u;
            }
        }

        if(user == null){
            throw new NoUserFoundException("User Not Found");
        }
        UserResponseDto userResponseDto = UserTransformer.convertEntityToDto(user);
        return userResponseDto;
    }

}
