package com.flowtech.fiadoapi.model.dto.client;

import com.flowtech.fiadoapi.model.dto.company.CompanyDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTO {

    private Long id;
    private String name;
    private String phone;
    private LocalDateTime createdAt;
    private CompanyDTO company;
}
