package com.razat.todo.dtos;

import com.razat.todo.models.User;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private List<AddressResponseDTO> addressResponseDTOList;
    public static UserResponseDTO toUserResponseDTO(User user){
        UserResponseDTO userResponseDTO = UserResponseDTO
                .builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .addressResponseDTOList(user.getAddressList().stream().map(address -> AddressResponseDTO.toAddressResponseDTO(address)).collect(Collectors.toList()))
                .build();
        return userResponseDTO;
    }
}
