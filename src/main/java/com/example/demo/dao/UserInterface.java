package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entiy.User;

@Repository
public interface UserInterface extends JpaRepository<User, Long> {

}
