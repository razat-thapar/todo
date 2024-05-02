package com.razat.todo.services;

import com.razat.todo.exceptions.AddressNotFoundException;
import com.razat.todo.models.Address;
import com.razat.todo.repositories.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.razat.todo.exceptions.InvalidAddressException;

import java.util.Optional;

@Service
public class AddressService {
    private AddressRepository addressRepository;
    private Logger logger = LoggerFactory.getLogger(AddressService.class);
    @Autowired
    public AddressService(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }
    public Address createAddress(Address address) {
        logger.info("validate the address!");
        //1. perform validation.
        validate(address);
        //2. saved it in persistence store using respository layer.
        //3. return the savedaddress
        logger.info("validation successfully, saving the address in persistence store!");
        return addressRepository.save(address);
    }

    private void validate(Address address) {
        if(address.getAddressLine1()==null || address.getCity()==null || address.getCountry()==null || address.getPincode()==null || address.getAddressType()==null){
            throw new InvalidAddressException("AddressLine1, city, country , pincode , address type can't be null!");
        }
    }

    public Address getAddressById(Long id) {
        logger.info("trying to fetching the address from persistence store using id {} ",id);
        //1. try to get the address using respository layer.
        Optional<Address> addressOptional = addressRepository.findById(id);
        //2. if not foudn then throw exception.
        return addressOptional.orElseThrow(()->new AddressNotFoundException("Address doesn't exist with id: "+id));
    }
}
