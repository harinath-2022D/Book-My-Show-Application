package com.example.BookMyShow.Transformers;

import com.example.BookMyShow.Models.User;
import com.example.BookMyShow.RequestDtos.AddUserDto;
import com.example.BookMyShow.ResponseDtos.UserResponseDto;

public class UserTransformer {
    public static User convertDtoTOEntity(AddUserDto addUserDto){
        // general way
//        User user = new User();
//        user.setName(addUserDto.getName());
//        user.setAge(addUserDto.getAge());
//        user.setMobileNo(addUserDto.getMobilNo());
//        user.setEmailId(addUserDto.getEmailId());
//
//        return user;

        // professional way
        User user = User.builder()
                .name(addUserDto.getName())
                .age(addUserDto.getAge())
                .mobileNo(addUserDto.getMobileNo())
                .emailId(addUserDto.getEmailId())
                .build();

        return user;
    }
    public static UserResponseDto convertEntityToDto(User user){

        UserResponseDto userResponseDto = UserResponseDto.builder()
                .name(user.getName())
                .age(user.getAge())
                .mobileNo(user.getMobileNo())
                .emailId(user.getEmailId())
                .build();

        return userResponseDto;
    }
}
