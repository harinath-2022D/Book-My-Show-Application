package com.example.BookMyShow.Transformers;

import com.example.BookMyShow.Models.Theater;
import com.example.BookMyShow.RequestDtos.AddTheaterDto;

public class ThaterTransformer {

    public static Theater convertDtoToEntity(AddTheaterDto addTheaterDto){

        Theater theater = Theater.builder()
                .name(addTheaterDto.getName())
                .location(addTheaterDto.getLocation())
                .build();

        return theater;
    }
}
