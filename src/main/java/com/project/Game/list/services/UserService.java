package com.project.Game.list.services;

import com.project.Game.list.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.Game.list.dto.user.UserRequestDTO;
import com.project.Game.list.entities.user.User;
import com.project.Game.list.entities.user.UserRole;
import com.project.Game.list.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserRepository userRepository;

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public void insertUser(UserRequestDTO user) {
        var verifyUser = userRepository.findByLogin(user.getLogin());
        if (verifyUser != null) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        var userToSave = new User(user.getName(), user.getLogin(), passwordEncoder.encode(user.getPassword()), UserRole.USER );
        userRepository.save(userToSave);
    }

    public void deleteUser(Long id, String userFromRequest){
        var user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if (user.getLogin().equals(userFromRequest)){
            userRepository.deleteById(user.getId());
        }
        else throw new IllegalArgumentException("Operação invalida");
    }
}
