package com.razat.todo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.razat.todo.exceptions.InvalidUserException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name= "user")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseModel{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @OneToMany(mappedBy = "user",cascade= CascadeType.PERSIST)
    private List<Address> addressList = new ArrayList<>();

    public static UserBuilder builder(){
        return new UserBuilder();
    }
    private User(User other){
        super(other);
        this.name = other.name;
        this.email = other.email;
        this.addressList = other.addressList;
    }

    public static class UserBuilder{
        private User user = new User();

        public User build(){
            //perform complex validatiosn.
            validate(user);
            //deep copy.
            return new User(user);
        }
        private static void validate(User user){
            if(user.email== null || user.name == null){
                throw new InvalidUserException("User can't have null email and name!");
            }
        }
        public UserBuilder name(String name){
            user.name = name;
            return this;
        }
        public UserBuilder email(String email){
            user.email = email;
            return this;
        }
        public UserBuilder addressList(List<Address> addressList){
            user.addressList = addressList;
            return this;
        }

    }
}
