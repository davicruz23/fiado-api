package com.flowtech.fiadoapi.controller.mapper;

import com.flowtech.fiadoapi.model.Company;
import com.flowtech.fiadoapi.model.dto.company.CompanyDTO;

public class CompanyMapper {
    public static CompanyDTO mapper(Company src) {
        return CompanyDTO.builder()
                .id(src.getId())
                .name(src.getName())
                .cpf(src.getCpf())
                .isActive(src.getActive())
                .createdAt(src.getCreatedAt().toLocalDateTime())
                .build();
    }
}
