package com.flowtech.fiadoapi.model;

import com.flowtech.fiadoapi.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType position;
    private boolean active;
}
