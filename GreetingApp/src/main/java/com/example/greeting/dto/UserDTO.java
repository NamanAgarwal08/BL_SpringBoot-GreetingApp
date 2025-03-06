package com.example.greeting.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long Id;

    @NotBlank(message = "Enter first name!")
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "firstname's first letter should be capital")
    private String firstname;

    @NotBlank(message = "Enter last name!")
    @Pattern(regexp = "^[A-Z][a-z]*$", message = "lastname's first letter should be capital")
    private String lastname;

    @NotBlank(message = "Email field can't be left blank!")
    @Email(message = "Enter email in correct format!")
    private String email;

    @NotBlank(message = "Password field can't be left blank!")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[@#$%^&*()\\-+=])(?=.*\\d).{8,}$", message = "Your password should have at least one uppercase letter, at least one special character, at least one digit and minimum 8 characters long.")
    private String password;

}
