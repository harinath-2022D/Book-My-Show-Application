package com.example.BookMyShow.Models;

import com.example.BookMyShow.Enums.Genre;
import com.example.BookMyShow.Enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    private int duration;
    private double rating;
    private Date releaseDate;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Enumerated(EnumType.STRING)
    private Language language;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL) // bi-directional mapping
    private List<Show> showList = new ArrayList<>();

}
