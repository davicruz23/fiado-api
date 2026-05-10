package com.flowtech.fiadoapi.model.dto.company;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpsertCompanyDTO {

    private String name;
    private String cpf;
    private String password;
}
