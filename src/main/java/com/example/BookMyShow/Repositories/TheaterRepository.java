package com.example.BookMyShow.Repositories;

import com.example.BookMyShow.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater,Integer> {

    Theater findByLocation(String location);
}
