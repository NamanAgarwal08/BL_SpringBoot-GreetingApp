package com.example.greeting.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long Id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

}
