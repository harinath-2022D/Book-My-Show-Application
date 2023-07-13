package com.example.BookMyShow.Repositories;

import com.example.BookMyShow.Models.Ticket;
import com.example.BookMyShow.Models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

}
