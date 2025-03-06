package com.example.greeting.services;

import com.example.greeting.dto.LoginDTO;
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

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private final JsonWebTokenService jsonWebTokenService;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder bCryptPasswordEncoder, JsonWebTokenService jsonWebTokenService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jsonWebTokenService = jsonWebTokenService;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userEntity -> modelMapper.map(userEntity, UserDTO.class)).collect(Collectors.toList());
    }



    //User Registration
    public String register(UserDTO userDTO) {

        UserEntity userEntity = userRepository.findByEmail(userDTO.getEmail());
        if(userEntity!=null){
            return "Email id already exists!";
        }


        //creating hash password
        String hashPass = bCryptPasswordEncoder.encode(userDTO.getPassword());

        userEntity = modelMapper.map(userDTO, UserEntity.class);

        //explicitly saving hashed password in repository
        userEntity.setPassword(hashPass);
        userRepository.save(userEntity);

        try{
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(userDTO.getEmail());
            simpleMailMessage.setSubject("Registration Status");
            simpleMailMessage.setText(userDTO.getFirstname()+",\n "+"Your registration in Greeting App is successfully done!");

            javaMailSender.send(simpleMailMessage);
        }catch(Exception e){
            return "Unable to send email!";
        }

        return "User registered successfully!";

    }


    //User Login
    public String login(LoginDTO loginDTO) {
        UserEntity userEntity = userRepository.findByEmail(loginDTO.getEmail());
        if(userEntity==null){
            return "Email does not exists.\nPlease register first";
        }

        if(!bCryptPasswordEncoder.matches(loginDTO.getPassword(), userEntity.getPassword())){
            return "Enter correct password";
        }

        String token = jsonWebTokenService.createToken(userEntity.getId());

        return "Login successful!\nToken: " + token;
    }

}