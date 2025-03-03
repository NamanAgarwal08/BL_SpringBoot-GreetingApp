package com.example.greeting.services;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public String getGreetings(){
        return "Hello World!";
    }
}
