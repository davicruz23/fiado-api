package com.flowtech.fiadoapi.model.dto.apiError;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.validation.FieldError;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private Integer status;
    private String error;
    private String message;
    private String path;
    private LocalDateTime timestamp;
    private List<ApiFieldError> fieldErrors = new ArrayList<>();;

    public ApiError(
            Integer status,
            String error,
            String message,
            String path
    ) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
}