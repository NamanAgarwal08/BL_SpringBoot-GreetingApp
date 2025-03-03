package com.example.greeting.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MessageDTO {
    private String message;

    private Long id;

    public MessageDTO(String message) {
        this.message = message;
        this.id = null;
    }

}