package com.project.Game.list.controllers;

import java.net.URI;
import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.Game.list.dto.game.GameDTO;
import com.project.Game.list.dto.game.GameMinDTO;
import com.project.Game.list.dto.game.GameRequestDTO;
import com.project.Game.list.entities.game.Game;
import com.project.Game.list.services.GameService;

@RestController
@RequestMapping(value = "/games")
@Tag(name = "Game controller", description = "The endpoint for games information")
public class GameController {

	@Autowired
	private GameService gameService;

	@GetMapping
	public List<GameMinDTO> findAll() {
		List<GameMinDTO> lista = gameService.findAll();
		return lista;
	}

	@GetMapping(value = "/{id}")
	public GameDTO findById(@PathVariable Long id) {
		GameDTO game = gameService.findById(id);
		return game;
	}

	@PostMapping
	public ResponseEntity<Void> insertGame(@RequestBody GameRequestDTO entity) {
		Game game = new Game(entity);
		gameService.insertGame(game);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/games/{id}").buildAndExpand(game.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value = "/like/{id}")
	public ResponseEntity<Void> likeGame(Authentication userRequest, @PathVariable Long id) {
		gameService.likeGame(userRequest.getName(), id);
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/unlike/{id}")
	public ResponseEntity<Void> unlikeGame(Authentication userRequest, @PathVariable Long id) {
		gameService.unlikeGame(userRequest.getName(), id);
		return ResponseEntity.ok().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> modifyGame(@PathVariable Long id, @RequestBody GameDTO entity) {
		Game game = new Game(entity);
		game.setId(id);
		gameService.insertGame(game);
		return ResponseEntity.status(201).build();
	}

	@DeleteMapping(value = "/{id}")
	public void deleteById(@PathVariable Long id) {
		gameService.deleteById(id);
	}
}