package com.razat.todo.controllers;

import com.razat.todo.dtos.CreateUserRequestDTO;
import com.razat.todo.dtos.UserResponseDTO;
import com.razat.todo.models.User;
import com.razat.todo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    //create
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody CreateUserRequestDTO userRequestDTO){
        //create user and persist in persistence store.
        User user = userService.createUser(userRequestDTO.toUser());
        return new ResponseEntity<>(UserResponseDTO.toUserResponseDTO(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id){
        //1. fetch user using id.
        User user = userService.getUserById(id);
        //2. return user.
        return new ResponseEntity<>(UserResponseDTO.toUserResponseDTO(user),HttpStatus.FOUND);
    }
}
