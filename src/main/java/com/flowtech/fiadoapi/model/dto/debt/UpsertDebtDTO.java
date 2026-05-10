package com.flowtech.fiadoapi.model.dto.debt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertDebtDTO {

    private BigDecimal amount;
    private String description;
    private Long clientId;
}
