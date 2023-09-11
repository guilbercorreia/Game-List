package com.project.Game.list.services.exceptions;

public class IntegrityViolationExcepion extends RuntimeException {
    
    public IntegrityViolationExcepion(Object id){
        super("Remoção indevida do jogo " + id);
    }
}
