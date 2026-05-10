package com.flowtech.fiadoapi.model.dto.client;

import com.flowtech.fiadoapi.model.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpsertClientDTO {

    private String name;
    private String phone;
}
