package com.project.Game.list.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Game.list.entities.Game;

public interface GameRepository extends JpaRepository<Game, Long>{
    
}
