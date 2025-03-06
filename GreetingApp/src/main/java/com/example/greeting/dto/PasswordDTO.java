package com.example.greeting.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDTO {

    @NotBlank(message = "Enter password!")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[@#$%^&*()\\-+=])(?=.*\\d).{8,}$", message = "Your password should have at least one uppercase letter, at least one special character, at least one digit and minimum 8 characters long.")
    private String password;

}
