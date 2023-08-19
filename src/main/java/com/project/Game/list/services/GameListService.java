package com.project.Game.list.services;

import com.project.Game.list.dto.GameListDTO;
import com.project.Game.list.entities.GameList;
import com.project.Game.list.repositories.GameListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;
    
    public List<GameListDTO> findAll(){
        List<GameList> lista = gameListRepository.findAll();
        return lista.stream().map(x -> new GameListDTO(x)).toList();
    }
}
