package com.project.Game.list.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.project.Game.list.dto.GameDTO;
import com.project.Game.list.dto.GameMinDTO;
import com.project.Game.list.entities.Game;
import com.project.Game.list.projections.GameMinProjection;
import com.project.Game.list.repositories.GameRepository;
import com.project.Game.list.services.exceptions.GameNotFoundException;
import com.project.Game.list.services.exceptions.IntegrityViolationExcepion;

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
        Optional<Game> result = gameRepository.findById(id);
        result.orElseThrow(() -> new GameNotFoundException(id));
        return new GameDTO(result.get());
    }

    public Game insertGame(Game entity){
        return gameRepository.save(entity);
    }

    public void deleteById(Long id){
        Optional<Game> result = gameRepository.findById(id);
        result.orElseThrow(() -> new GameNotFoundException(id));
        try{
         gameRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new IntegrityViolationExcepion(id);
        }
    }
}        