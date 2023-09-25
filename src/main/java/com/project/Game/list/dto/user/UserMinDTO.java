package com.project.Game.list.dto.user;

import com.project.Game.list.entities.user.User;
import com.project.Game.list.entities.user.UserRole;

public class UserMinDTO {

    private Long id;
    private String name;
    private UserRole role;
    
    public UserMinDTO() {
    }

    public UserMinDTO(User entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.role = entity.getRole();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
