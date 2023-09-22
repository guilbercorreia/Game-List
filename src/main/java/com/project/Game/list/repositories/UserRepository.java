package com.project.Game.list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Game.list.entities.user.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository <User, Long> {

    UserDetails findByLogin(String login);
}
