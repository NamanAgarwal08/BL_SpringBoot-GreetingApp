package com.example.greeting.configuration;

import com.example.greeting.services.JsonWebTokenService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {


    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    // for bcrypt (encoding passwords)
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JsonWebTokenService getService(){
        return new JsonWebTokenService();
    }

}
