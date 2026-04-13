package com.flowtech.fiadoapi.service;

import com.flowtech.fiadoapi.enums.UserType;
import com.flowtech.fiadoapi.exception.BusinessException;
import com.flowtech.fiadoapi.model.User;
import com.flowtech.fiadoapi.model.dto.user.UpsertUserDTO;
import com.flowtech.fiadoapi.record.LoginRequest;
import com.flowtech.fiadoapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public User login(LoginRequest request) {
        User user = userRepository.findByCpf(request.cpf())
                .orElseThrow(() -> new RuntimeException("Usuário não existe!"));

        System.out.println("CPF: " + user.getCpf());
        System.out.println("IS ACTIVE: " + user.isActive());

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Senha invalida!");
        }

        if (!user.isActive()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Usuário Inativo. Entre em Contato com o Suporte!");
        }
        return user;
    }

    public User register(UpsertUserDTO store) {
        if (userRepository.findByCpf(store.getCpf()).isPresent()) {
            throw new BusinessException("CPF já cadastrado!");
        }

        User user = new User();
        user.setName(store.getName());
        user.setCpf(store.getCpf());
        user.setPassword(passwordEncoder.encode(store.getPassword()));
        user.setActive(true);
        user.setPosition(UserType.fromValue(store.getPosition()));
        user = userRepository.save(user);

        return user;
    }
}
