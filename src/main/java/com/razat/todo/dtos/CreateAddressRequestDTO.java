package com.razat.todo.dtos;

import com.razat.todo.enums.AddressType;
import com.razat.todo.models.Address;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateAddressRequestDTO {
    private String city;
    private String country;
    private Long pincode;
    private String addressLine1;
    private AddressType addressType;
    private String addressLine2;//optional
    private Long userId; //optional.


    //mapper methods.
    public Address toAddress(){
        Address address = Address
                .builder()
                .addressLine1(addressLine1)
                .addressLine2(addressLine2)
                .city(city)
                .country(country)
                .pincode(pincode)
                .addressType(addressType)
                .build();
        return address;
    }

}
