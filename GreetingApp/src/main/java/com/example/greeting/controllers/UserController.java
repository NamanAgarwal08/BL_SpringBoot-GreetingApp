package com.example.greeting.controllers;


import com.example.greeting.dto.UserDTO;
import com.example.greeting.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO){
        return userService.register(userDTO);
    }

}