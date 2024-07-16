package com.example.spring_api.api.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.spring_api.api.model.User;
import com.example.spring_api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    //I acknowledge this is not the best way to do this.  Returning an entity with a network code...
    @GetMapping("/user")
    public User getUser(@RequestParam Integer id)
    {
        Optional<User> user = userService.getUser(id);
        if (user.isPresent())
        {
            return (User) user.get();
        }
        return null;
    }
}
