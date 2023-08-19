package com.project.Game.list.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Game.list.dto.GameDTO;
import com.project.Game.list.dto.GameMinDTO;
import com.project.Game.list.entities.Game;
import com.project.Game.list.repositories.GameRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    
    public List<GameMinDTO> findAll(){
        List<Game> lista = gameRepository.findAll();
        return lista.stream().map(x -> new GameMinDTO(x)).toList();
    }
     
    public GameDTO findById(Long id){
        Game result = gameRepository.findById(id).get();
        return new GameDTO(result);
    }
}
