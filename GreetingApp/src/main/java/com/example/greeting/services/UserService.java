package com.example.greeting.services;

import com.example.greeting.dto.UserDTO;
import com.example.greeting.entities.UserEntity;
import com.example.greeting.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.GregorianCalendar.BC;

@Service
public class UserService {

    @Autowired
    private JavaMailSender javaMailSender;

    final UserRepository userRepository;

    @Value("${spring.mail.username}") private String sender;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final PasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userEntity -> modelMapper.map(userEntity, UserDTO.class)).collect(Collectors.toList());
    }

    public String register(UserDTO userDTO) {

//        List<UserEntity> lst = userRepository.findAll().stream().filter(userEntity -> userDTO.getEmail().equals(userEntity.getEmail())).collect(Collectors.toList());
//
//        if(lst.size()>0){
//            return "Email id already exists!";
//        }

        UserEntity userEntity = userRepository.findByEmail(userDTO.getEmail());
        if(userEntity!=null){
            return "Email id already exists";
        }


        //creating hash password
        String hashPass = bCryptPasswordEncoder.encode(userDTO.getPassword());

        userEntity = modelMapper.map(userDTO, UserEntity.class);

        //explicitly saving hashed password in repository
        userEntity.setPassword(hashPass);
        userRepository.save(userEntity);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setTo(userDTO.getEmail());
        simpleMailMessage.setSubject("Registration Status");
        simpleMailMessage.setText("Your registration in Greeting App is successfully done!");

        javaMailSender.send(simpleMailMessage);

        return "User registered successfully!";

    }

}