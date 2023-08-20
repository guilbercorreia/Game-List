package com.project.Game.list.controllers;

import com.project.Game.list.dto.GameListDTO;
import com.project.Game.list.dto.GameMinDTO;
import com.project.Game.list.services.GameListService;
import com.project.Game.list.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/lists")
public class GameListController {
    
    @Autowired
    private GameListService gameListService;

    @Autowired
    private GameService gameService;

    @GetMapping(value = "/{listId}/games")
    public List<GameMinDTO> findByList(@PathVariable Long listId) {
        List<GameMinDTO> lista = gameService.findByList(listId);
        return lista;
    }

    @GetMapping
    public List<GameListDTO> findAll() {
        List<GameListDTO> lista = gameListService.findAll();
        return lista;
    }
}
