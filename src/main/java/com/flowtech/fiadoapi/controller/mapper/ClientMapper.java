package com.flowtech.fiadoapi.controller.mapper;

import com.flowtech.fiadoapi.model.Client;
import com.flowtech.fiadoapi.model.dto.client.ClientDTO;

public class ClientMapper {
    public static ClientDTO toDTO(Client src) {
        return ClientDTO.builder()
                .id(src.getId())
                .name(src.getName())
                .phone(src.getPhone())
                .createdAt(src.getCreatedAt().toLocalDateTime())
                .company(CompanyMapper.mapper(src.getCompany()))
                .build();
    }
}
