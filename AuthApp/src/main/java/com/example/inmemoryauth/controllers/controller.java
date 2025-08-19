package com.example.inmemoryauth.controllers;

import com.example.inmemoryauth.entities.MyUser;
import com.example.inmemoryauth.repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class controller {
    @Autowired
    private MyUserRepository myUserRepository;

    @GetMapping("/user/home")
    public String userHome(){
        return "userHome";
    }

    @GetMapping("/admin/home")
    public String adminHome(){
        return "adminHome";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/user/all-users")
    public String userAllUsers(Model model){
        List<MyUser> myUsers = myUserRepository.findAll();
        model.addAttribute("users",myUsers);
        return "userList";
    }
    @GetMapping("/users/new")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new MyUser());
        return "addUser"; // add-user.html
    }

    // Handle form submission
    @PostMapping("/users/save")
    public String saveUser(MyUser user) {
        myUserRepository.save(user);
        return "redirect:/user/all-users"; // redirect back to users list
    }
}
