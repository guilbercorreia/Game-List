package com.project.Game.list.services.exceptions;

public class IntegrityViolationException extends RuntimeException {
    
    public IntegrityViolationException(Object id){
        super("Remoção indevida do jogo " + id);
    }
}
