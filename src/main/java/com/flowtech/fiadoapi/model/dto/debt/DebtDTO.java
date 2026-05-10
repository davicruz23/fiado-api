package com.flowtech.fiadoapi.model.dto.debt;

import com.flowtech.fiadoapi.model.Client;
import com.flowtech.fiadoapi.model.Company;
import com.flowtech.fiadoapi.model.dto.client.ClientDTO;
import com.flowtech.fiadoapi.model.dto.company.CompanyDTO;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DebtDTO {

    private Long id;
    private BigDecimal amount;
    private String description;
    private LocalDateTime creationDate;
    private Boolean isPaid;
    private ClientDTO client;
    private CompanyDTO company;
}
