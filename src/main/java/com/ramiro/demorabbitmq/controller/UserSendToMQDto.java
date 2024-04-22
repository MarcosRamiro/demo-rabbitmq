package com.ramiro.demorabbitmq.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
public class UserSendToMQDto implements Serializable {

    @JsonProperty("nome")
    private String name;

    @JsonProperty("email")
    private String email;

    static UserSendToMQDto from(UserRequestDto requestDto) {
        return new UserSendToMQDto(requestDto.name(), requestDto.email());
    }

}