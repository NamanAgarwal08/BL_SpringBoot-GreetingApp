package com.example.greeting.services;

import com.example.greeting.dto.MessageDTO;
import com.example.greeting.entities.MessageEntity;
import com.example.greeting.repositories.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GreetingService {

    final MessageRepository messageRepository;
    private final ModelMapper modelMapper;

    public GreetingService(MessageRepository messageRepository, ModelMapper modelMapper) {
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
    }

    public String getGreetings(){
        return "Hello World!";
    }

    public MessageDTO saveMessage(MessageDTO message) {
        MessageEntity messageEntity = modelMapper.map(message, MessageEntity.class);
//        MessageRepository.save(messageEntity); // saves the entity and returns it
        return modelMapper.map(messageRepository.save(messageEntity), MessageDTO.class);
    }
}
