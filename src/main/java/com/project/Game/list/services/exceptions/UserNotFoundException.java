package com.project.Game.list.services.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Object id){
        super("Usuario com o id " + id + " não existe");
    }
}
