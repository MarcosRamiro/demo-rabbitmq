package com.ramiro.demorabbitmq.controller;

import com.ramiro.demorabbitmq.ServerRabbitMQ;
import com.ramiro.demorabbitmq.model.User;
import com.ramiro.demorabbitmq.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final ServerRabbitMQ sendMail;

    @PostMapping(path="/add")
    public ResponseEntity<UserResponseDto> addNewUser(@RequestBody UserRequestDto request)
            throws IOException {

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        userRepository.save(user);
        sendMail.send(
                String.format("Novo usuário %s Cadastrado com e-mail %s", user.getName(), user.getEmail()),
                "");
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
