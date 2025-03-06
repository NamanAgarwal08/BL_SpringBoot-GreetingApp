package com.example.greeting.controllers;


import com.example.greeting.dto.LoginDTO;
import com.example.greeting.dto.PasswordDTO;
import com.example.greeting.dto.UserDTO;
import com.example.greeting.services.UserService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class UserController {

    final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/getAll")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }



    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(userService.register(userDTO));
    }

//    @PostMapping("/login")
//    public String login(@RequestBody LoginDTO loginDTO){
//        return userService.login(loginDTO);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(userService.login(loginDTO));
    }



    @PutMapping("/forgotPassword/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable String email, @Valid @RequestBody PasswordDTO passwordDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }
        return ResponseEntity.ok(userService.forgotPassword(email, passwordDTO));
    }

    @PutMapping("/resetPassword/{email}")
    public String resetPassword(@PathVariable String email, @RequestParam String currentPass, @RequestParam String newPass){
        return userService.resetPassword(email, currentPass, newPass);
    }


}