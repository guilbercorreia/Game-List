package com.project.Game.list.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.Game.list.services.exceptions.GameNotFoundException;
import com.project.Game.list.services.exceptions.IntegrityViolationExcepion;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler(GameNotFoundException.class)
    public ResponseEntity<StandardError> gameNotFound(GameNotFoundException e, HttpServletRequest request){
        String error = "Game not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(IntegrityViolationExcepion.class)
    public ResponseEntity<StandardError> integrityViolation(IntegrityViolationExcepion e, HttpServletRequest request){
        String error = "Game cannot be deleted";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
