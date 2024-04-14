package com.ramiro.demorabbitmq.controller;

import com.ramiro.demorabbitmq.model.User;

public record UserResponseDto(String name, String email){

    public static UserResponseDto from(User user) {
        return new UserResponseDto(user.getName(), user.getEmail());
    }
}
