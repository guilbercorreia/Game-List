package com.project.Game.list.config;

import com.project.Game.list.entities.user.User;
import com.project.Game.list.entities.user.UserRole;
import com.project.Game.list.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@Profile("test")
public class Init implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User(1L, "joao", "joaocarlos@gmail.com", passwordEncoder.encode("12345678"), UserRole.ADMIN));
        userRepository.save(new User(2L, "abigail", "abigail.lopes@gmail.com", passwordEncoder.encode("1234444"), UserRole.USER));
    }
}
