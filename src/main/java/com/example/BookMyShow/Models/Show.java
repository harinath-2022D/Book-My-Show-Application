package com.example.BookMyShow.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "shows")
@Data
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalTime time;
    private Date date;

    @ManyToOne   // one movie can have multiple shows
    @JoinColumn
    private Movie movie;

    @ManyToOne  // one theater can have multiple shows
    @JoinColumn
    private Theater theater;
}
