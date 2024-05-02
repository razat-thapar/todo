package com.razat.todo.services;

import com.razat.todo.exceptions.InvalidUserException;
import com.razat.todo.exceptions.UserNotFoundException;
import com.razat.todo.models.User;
import com.razat.todo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public User getUserById(Long id) {
        logger.info("fetching user from persistence store! ");
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found with id:"+id));
        logger.info("found the user!!");
        return user;
    }

    public User createUser(User user) {
        //validate
        logger.info("Perform mandatory complex validation checks!!");
        validate(user);
        //persist in persistence store.
        logger.info("Persisting the user into persistence store!!");
        User savedUser = userRepository.save(user);
        logger.info("User persisted successfully!!");
        return savedUser;
    }

    private void validate(User user) {
        if(user.getName()==null || user.getEmail()==null){
            throw new InvalidUserException("User name and email should be non-null!!");
        }
    }
}
