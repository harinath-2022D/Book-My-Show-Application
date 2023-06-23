package com.example.BookMyShow.Repositories;

import com.example.BookMyShow.Models.Show;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Integer> {
}
