package com.example.BookMyShow.Repositories;

import com.example.BookMyShow.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "select * from users where age >= :age ;",nativeQuery = true)
    List<User> findUsersGreaterThanAge(int age);
}
