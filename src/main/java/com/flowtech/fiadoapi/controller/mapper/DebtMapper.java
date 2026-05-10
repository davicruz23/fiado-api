package com.flowtech.fiadoapi.controller.mapper;

import com.flowtech.fiadoapi.model.Debt;
import com.flowtech.fiadoapi.model.dto.debt.DebtDTO;

public class DebtMapper {
    public static DebtDTO toDTO(Debt src) {
        return DebtDTO.builder()
                .id(src.getId())
                .amount(src.getAmount())
                .description(src.getDescription())
                .isPaid(src.getPaid())
                .client(ClientMapper.toDTO(src.getClient()))
                .company(CompanyMapper.mapper(src.getCompany()))
                .build();

    }
}
