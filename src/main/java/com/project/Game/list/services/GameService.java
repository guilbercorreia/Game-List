package com.project.Game.list.services;

import java.util.List;
import java.util.Optional;

import com.project.Game.list.repositories.UserRepository;
import com.project.Game.list.services.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.project.Game.list.dto.game.GameDTO;
import com.project.Game.list.dto.game.GameMinDTO;
import com.project.Game.list.entities.game.Game;
import com.project.Game.list.projections.GameMinProjection;
import com.project.Game.list.repositories.GameRepository;
import com.project.Game.list.services.exceptions.GameNotFoundException;
import com.project.Game.list.services.exceptions.IntegrityViolationException;

@Service
public class GameService {

    @Autowired
    private UserRepository userRepository;
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
            throw new IntegrityViolationException(id);
        }
    }

    public void likeGame(String user, Long id) {
       var game = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
       var userFromRepository = userRepository.findByLogin(user).orElseThrow(() -> new UserNotFoundException(""));
       if (userFromRepository.getLikeGames().contains(game)){
           throw new IllegalArgumentException("Jogo já curtido");
       }
       userFromRepository.getLikeGames().add(game);
       userRepository.save(userFromRepository);
    }

	public void unlikeGame(String user, Long id) {
		var game = gameRepository.findById(id).orElseThrow(() -> new GameNotFoundException(id));
	    var userFromRepository = userRepository.findByLogin(user).orElseThrow(() -> new UserNotFoundException(""));
	    if(userFromRepository.getLikeGames().contains(game)) {
	    	userFromRepository.getLikeGames().remove(game);
	    	userRepository.save(userFromRepository);
	    }
	    else throw new IllegalArgumentException("Você não curtiu esse jogo");
	}
}