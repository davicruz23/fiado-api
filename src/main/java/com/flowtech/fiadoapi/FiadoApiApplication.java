package com.flowtech.fiadoapi;

import com.flowtech.fiadoapi.enums.UserType;
import com.flowtech.fiadoapi.model.User;
import com.flowtech.fiadoapi.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class FiadoApiApplication {

    public final PasswordEncoder passwordEncoder;

    public FiadoApiApplication(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    @Transactional
    public CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            if (userRepository.findByCpf("1").isEmpty()) {
                List<User> users = new ArrayList<>();
                users.add(new User(null, "1", "Davi Cruz", passwordEncoder.encode("123456"), UserType.SUPERADMIN, true));
                users.add(new User(null, "2", "Neo Mercadinho", passwordEncoder.encode("123456"), UserType.CLIENTE, true));
                userRepository.saveAll(users);
            }
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(FiadoApiApplication.class, args);
    }

}
