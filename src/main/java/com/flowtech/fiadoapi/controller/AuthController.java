package com.flowtech.fiadoapi.controller;

import com.flowtech.fiadoapi.controller.mapper.UserMapper;
import com.flowtech.fiadoapi.exception.BusinessException;
import com.flowtech.fiadoapi.model.User;
import com.flowtech.fiadoapi.model.dto.token.RefreshRequest;
import com.flowtech.fiadoapi.model.dto.user.UpsertUserDTO;
import com.flowtech.fiadoapi.model.dto.user.UserDTO;
import com.flowtech.fiadoapi.record.LoginRequest;
import com.flowtech.fiadoapi.record.LoginResponse;
import com.flowtech.fiadoapi.repository.UserRepository;
import com.flowtech.fiadoapi.security.TokenService;
import com.flowtech.fiadoapi.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        User user = service.login(request);

        if (user != null) {
            String accessToken = tokenService.generateToken(user);
            String refreshToken = tokenService.generateRefreshToken(user);
            return ResponseEntity.ok(new LoginResponse(accessToken, refreshToken));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha inválidos!");
        }
    }

    @PreAuthorize("hasAnyRole('SUPERADMIN')")
    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UpsertUserDTO userDTO) {
        return ResponseEntity.ok(UserMapper.toDTO(service.register(userDTO)));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody RefreshRequest request) {
        String refreshToken = request.getRefreshToken();

        String cpf;
        try {
            cpf = tokenService.getCpfFromRefreshToken(refreshToken);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Refresh token inválido ou expirado");
        }

        User user = userRepository.findByCpf(cpf)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado!"));

        String newAccessToken = tokenService.generateToken(user);
        String newRefreshToken = tokenService.generateRefreshToken(user);

        return ResponseEntity.ok(new LoginResponse(newAccessToken, newRefreshToken));
    }
}
