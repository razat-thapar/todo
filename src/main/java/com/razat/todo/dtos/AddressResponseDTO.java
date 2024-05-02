package com.razat.todo.dtos;

import com.razat.todo.enums.AddressType;
import com.razat.todo.models.Address;
import com.razat.todo.models.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AddressResponseDTO {
    private Long id;
    private String city;
    private String country;
    private Long pincode;
    private String addressLine1;
    private String addressLine2;
    private User user;
    private AddressType addressType;

    //mapper methods.
    public static AddressResponseDTO toAddressResponseDTO(Address address){
        AddressResponseDTO addressResponseDTO = AddressResponseDTO
                .builder()
                .id(address.getId())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .city(address.getCity())
                .country(address.getCountry())
                .pincode(address.getPincode())
                .user(address.getUser())
                .addressType(address.getAddressType())
                .build();
        return addressResponseDTO;
    }
}
