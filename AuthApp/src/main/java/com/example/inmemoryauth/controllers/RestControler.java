package com.example.inmemoryauth.controllers;

import com.example.inmemoryauth.entities.MyUser;
import com.example.inmemoryauth.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestControler {

    @Autowired
    private MyUserRepository myUserRepository;

  @PostMapping("/register/save")
    public MyUser registerUser(@RequestBody MyUser user){
      return myUserRepository.save(user);
  }

  @GetMapping("/user/get-all")
    public List<MyUser> getUser(){
      return myUserRepository.findAll();
  }
}
