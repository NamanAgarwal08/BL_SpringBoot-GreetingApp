package com.example.greeting.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotBlank(message = "Email field can't be left blank!")
    @Email(message = "Enter email in correct format!")
    private String email;

    @NotBlank(message = "Password field can't be left blank!")
    private String password;

}