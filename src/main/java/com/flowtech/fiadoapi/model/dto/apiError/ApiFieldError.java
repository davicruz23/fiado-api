package com.flowtech.fiadoapi.model.dto.apiError;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiFieldError {

    private String field;
    private String message;
}
