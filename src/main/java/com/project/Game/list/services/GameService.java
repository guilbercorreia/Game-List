package com.project.Game.list.services;

import com.project.Game.list.dto.GameDTO;
import com.project.Game.list.dto.GameMinDTO;
import com.project.Game.list.entities.Game;
import com.project.Game.list.projections.GameMinProjection;
import com.project.Game.list.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;
    
    public List<GameMinDTO> findAll(){
        List<Game> lista = gameRepository.findAll();
        return lista.stream().map(x -> new GameMinDTO(x)).toList();
    }

    public List<GameMinDTO> findByList(Long listId){
        List<GameMinProjection> lista = gameRepository.searchByList(listId);
        return lista.stream().map(x -> new GameMinDTO(x)).toList();
    }

    public GameDTO findById(Long id){
        Game result = gameRepository.findById(id).get();
        return new GameDTO(result);
    }
}
