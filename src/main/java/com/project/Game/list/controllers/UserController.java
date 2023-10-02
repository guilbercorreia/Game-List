package com.project.Game.list.controllers;

import com.project.Game.list.dto.user.UserRequestDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.project.Game.list.dto.user.UserMinDTO;
import com.project.Game.list.services.UserService;


@RestController
@RequestMapping(value = "/users")
@Tag(name = "User controller", description = "The endpoint for user management")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/{id}")
    public ResponseEntity<UserMinDTO> findById(@PathVariable Long id) {
        var dto = new UserMinDTO(userService.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> insertUser(@RequestBody UserRequestDTO user){
        userService.insertUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removeUser(Authentication username, @PathVariable Long id){
        userService.deleteUser(id, username.getName());
        return ResponseEntity.ok().build();
    }
}
