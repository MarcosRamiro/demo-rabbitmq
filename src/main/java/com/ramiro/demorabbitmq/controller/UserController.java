package com.ramiro.demorabbitmq.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramiro.demorabbitmq.model.User;
import com.ramiro.demorabbitmq.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private RabbitTemplate rabbitTemplate;
    private ObjectMapper mapper;

    @PostMapping(path="/add")
    public ResponseEntity<UserResponseDto> addNewUser(@RequestBody UserRequestDto request) {

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        //userRepository.save(user);
        rabbitTemplate.convertAndSend("new/user" , UserSendToMQDto.from(request));
        return ResponseEntity.ok(UserResponseDto.from(user));
    }

    @GetMapping(path="/all")
    public List<UserResponseDto> getAllUsers() {
        List<UserResponseDto> users = new ArrayList<>();
        for (User user : userRepository.findAll()){
            users.add(UserResponseDto.from(user));
        }
        return users;
    }
}
