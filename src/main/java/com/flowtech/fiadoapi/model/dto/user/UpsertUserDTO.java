package com.flowtech.fiadoapi.model.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertUserDTO {

    private String name;
    private String cpf;
    private String password;
    private boolean active;
    private Integer position;

}
