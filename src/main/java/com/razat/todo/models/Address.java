package com.razat.todo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.razat.todo.enums.AddressType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Setter
public class Address extends BaseModel{
    private String city;
    private String country;
    private Long pincode;
    private String addressLine1;
    private String addressLine2;
    private AddressType addressType;
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    public static AddressBuilder builder(){
        return new AddressBuilder();
    }
    public Address(){

    }
    //copy constructor.
    public Address(Address other){
        super(other);
        this.city = other.city;
        this.country = other.country;
        this.pincode = other.pincode;
        this.addressLine1 = other.addressLine1;
        this.addressLine2 = other.addressLine2;
        this.addressType = other.addressType;
    }

    public static class AddressBuilder{
        //to hold the input details of address.
        private Address address = new Address();
        //expose all setter methods that return AddressBuilder object.
        public AddressBuilder city(String city){
            address.city = city;
            return this;
        }
        public AddressBuilder country(String country){
            address.country = country;
            return this;
        }
        public AddressBuilder pincode(Long pincode){
            address.pincode = pincode;
            return this;
        }
        public AddressBuilder addressLine1(String addressLine1){
            address.addressLine1 = addressLine1;
            return this;
        }
        public AddressBuilder addressLine2(String addressLine2){
            address.addressLine2 = addressLine2;
            return this;
        }
        public AddressBuilder addressType(AddressType addressType){
            address.addressType = addressType;
            return this;
        }
        //expose a terminal method build() to return Address object.
        public Address build(){
            //perform custom validation.
            //deep copy the address details.
            Address newAddress = new Address(this.address);
            return newAddress;
        }
    }
}
