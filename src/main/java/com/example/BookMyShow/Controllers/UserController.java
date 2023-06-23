package com.example.BookMyShow.Controllers;

import com.example.BookMyShow.Models.User;
import com.example.BookMyShow.RequestDtos.AddUserDto;
import com.example.BookMyShow.ResponseDtos.UserResponseDto;
import com.example.BookMyShow.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/add-user")
    public String addUser(@RequestBody AddUserDto addUserDto){

        try{
            String response = userService.addUser(addUserDto);
            return response;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/oldestUser")
    public UserResponseDto getOldestUser(){
        try{
            UserResponseDto userResponseDto = userService.getOldestUser();

            userResponseDto.setStatusCode("200");
            userResponseDto.setStatusMessage("SUCCESS");

            return userResponseDto;
        }catch(Exception e){
            UserResponseDto userResponseDto = new UserResponseDto();

            userResponseDto.setStatusCode("404");
            userResponseDto.setStatusMessage("NOT FOUND");

            return userResponseDto;
        }
    }

    @GetMapping("/UsersGreaterThanAge")
    public List<User> findUsersGreaterThanAge(@RequestParam("age") int age){
        return userService.findUsersGreaterThanAge(age);
    }
}
