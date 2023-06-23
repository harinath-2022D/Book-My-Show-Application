package com.example.BookMyShow.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String name;
    private int age;
    private String mobileNo;
    private String emailId;


    private String statusCode;
    private String statusMessage;
}
