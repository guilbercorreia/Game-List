package com.project.Game.list.repositories;

import com.project.Game.list.entities.GameList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameListRepository extends JpaRepository<GameList, Long>{

}
