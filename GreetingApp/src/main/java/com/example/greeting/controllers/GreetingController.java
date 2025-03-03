package com.example.greeting.controllers;

import com.example.greeting.dto.MessageDTO;
import com.example.greeting.services.GreetingService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("greetings")
public class GreetingController {

    final GreetingService greetingService;

    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    //for UC1
    @GetMapping("/get")
    public String getGreetings(){

        return "{\"message\": \"Hello from GET Request!\"}";
    }

    @PostMapping("/post")
    public String postGreetings(@RequestBody MessageDTO message){
        return "{\""+message.getMessage()+": \"Hello from POST Request!\"}";
    }

    @PutMapping("/put/{message}")
    public String putGreetings(@PathVariable String message){
        return "{\""+message+": \"Hello from PUT Request!\"}";
    }



    //for UC2
    @GetMapping("/service")
    public String serviceGreetings(){
        return greetingService.getGreetings();
    }

    //for UC3
    @GetMapping("/query")
    public String query(@PathParam("firstName") String firstName,
                        @PathParam("lastName") String lastName){
        if(firstName != null && lastName != null)
            return "Hello " + firstName + " " + lastName;
        else if(firstName != null)
            return "Hello "+firstName;
        else if(lastName != null)
            return "Hello "+lastName;
        else
            return "Hello World";
    }

    //for UC4
    @PostMapping("/save")
    public MessageDTO saveMessage(@RequestBody MessageDTO message){
        System.out.println("hello");
        return greetingService.saveMessage(message);
    }
}
