package com.project.Game.list.services;

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
        return userRepository.findById(id).get();
    }

    public void insertUser(UserRequestDTO user)  {
        User userToSave = new User(user.getName(), user.getLogin(), passwordEncoder.encode(user.getPassword()), UserRole.USER );
        userRepository.save(userToSave);
    }

    public void deleteUser(Long id, String userfromrequest){
        var user = userRepository.findById(id).get();
        if (user.getLogin().equals(userfromrequest)){
            userRepository.deleteById(user.getId());
        }
    }
}
