package com.project.Game.list.services.exceptions;

public class GameNotFoundException extends RuntimeException {

    public GameNotFoundException(Object id){
        super("Jogo não encontrado pelo ID " + id);
    }
}
