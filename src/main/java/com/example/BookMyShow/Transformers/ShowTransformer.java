package com.example.BookMyShow.Transformers;

import com.example.BookMyShow.Models.Show;
import com.example.BookMyShow.RequestDtos.AddShowDto;

public class ShowTransformer {

    public static Show convertDtoToEntity(AddShowDto addShowDto){

        Show show = Show.builder()
                .date(addShowDto.getShowDate())
                .time(addShowDto.getShowStartTime())
                .build();

        return show;
    }
}
