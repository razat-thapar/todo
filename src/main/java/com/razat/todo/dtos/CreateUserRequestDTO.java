package com.razat.todo.dtos;

import com.razat.todo.models.User;
import lombok.Getter;

@Getter
public class CreateUserRequestDTO {
    private String name;
    private String email;
    public User toUser(){
        User user = User
                .builder()
                .name(name)
                .email(email)
                .build();
        return user;
    }
}
