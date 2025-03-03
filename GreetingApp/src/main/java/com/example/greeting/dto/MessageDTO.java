package com.example.greeting.dto;

public class MessageDTO {
    private String message;

    private Long id;

    public MessageDTO(String message) {
        this.message = message;
        this.id = null;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public Long getId() {

        return id;
    }

    public void setMessage(String message) {

        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}