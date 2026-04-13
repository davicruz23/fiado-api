package com.flowtech.fiadoapi.controller.mapper;

import com.flowtech.fiadoapi.model.User;
import com.flowtech.fiadoapi.model.dto.user.UserDTO;

public class UserMapper {
    public static UserDTO toDTO (User src) {
        return UserDTO.builder()
                .id(src.getId())
                .name(src.getName())
                .cpf(src.getCpf())
                .active(src.isActive())
                .position(src.getPosition().name())
                .build();
    }
}
