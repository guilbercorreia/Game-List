package com.project.Game.list.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.Game.list.dto.GameDTO;
import com.project.Game.list.dto.GameMinDTO;
import com.project.Game.list.entities.Game;
import com.project.Game.list.services.GameService;

@RestController
@RequestMapping(value = "/games")
public class GameController {
    
    @Autowired
    private GameService gameService;

    @GetMapping
    public List<GameMinDTO> findAll(){
        List<GameMinDTO> lista = gameService.findAll();
        return lista;
    }

    @GetMapping(value = "/{id}")
    public GameDTO findById(@PathVariable Long id){
        GameDTO game = gameService.findById(id);
        return game;
    } 

    @PostMapping
    public ResponseEntity<Void> insertGame(@RequestBody GameDTO entity){
        Game game = new Game(entity);
        gameService.insertGame(game);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/games/{id}").buildAndExpand(game.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
