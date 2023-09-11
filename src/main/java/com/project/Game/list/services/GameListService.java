package com.project.Game.list.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Game.list.dto.GameListDTO;
import com.project.Game.list.entities.GameList;
import com.project.Game.list.projections.GameMinProjection;
import com.project.Game.list.repositories.GameListRepository;
import com.project.Game.list.repositories.GameRepository;

import jakarta.transaction.Transactional;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;
    
    public List<GameListDTO> findAll(){
        List<GameList> lista = gameListRepository.findAll();
        return lista.stream().map(x -> new GameListDTO(x)).toList();
    }

    @Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {

		List<GameMinProjection> list = gameRepository.searchByList(listId);

		GameMinProjection obj = list.remove(sourceIndex);
		list.add(destinationIndex, obj);

		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex : sourceIndex;

		for (int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
    }

    public void addList(GameListDTO list){
        gameListRepository.save(new GameList(list));
    }
}
