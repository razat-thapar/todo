package com.razat.todo.controllers;

import com.razat.todo.dtos.AddressResponseDTO;
import com.razat.todo.dtos.CreateAddressRequestDTO;
import com.razat.todo.models.Address;
import com.razat.todo.models.User;
import com.razat.todo.services.AddressService;
import com.razat.todo.services.UserService;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/addresses")
public class AddressController {
    private AddressService addressService;
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(AddressController.class);
    @Autowired
    public AddressController(AddressService addressService, UserService userService){
        this.addressService=addressService;
        this.userService = userService;
    }
    //Create
    @PostMapping
    public ResponseEntity<AddressResponseDTO> createAddress(@RequestBody CreateAddressRequestDTO createAddressRequestDTO){
        //convert to address.
        Address address = createAddressRequestDTO.toAddress();
        //check if user exists if userId is non-null.
        logger.info("Check if user id is provided? ");
        Long userId = createAddressRequestDTO.getUserId();
        if(userId!=null){
            //set the user in address.
            logger.info("get the user using id and map it to address!");
            address.setUser(userService.getUserById(userId));
        }
        //pass the address create to service layer.
        Address savedAddress = addressService.createAddress(address);
        logger.info("address succesfully created!!");
        return new ResponseEntity<AddressResponseDTO>(AddressResponseDTO.toAddressResponseDTO(savedAddress), HttpStatus.CREATED);
    }
    //get 1
    @GetMapping("/{id}")
    public ResponseEntity<AddressResponseDTO> getAddressById(@PathVariable("id") Long id){
        //1. try to get the address using service layer
        Address address = addressService.getAddressById(id);
        logger.info("address succesfully fetched!!");
        //2. convert the address to address response dto and return response enityt.
        return new ResponseEntity<>(AddressResponseDTO.toAddressResponseDTO(address),HttpStatus.FOUND);
    }
    //get all
    //update
    //delete
}
