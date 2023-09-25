package com.project.Game.list.controllers.exceptions;

import java.time.Instant;

import com.project.Game.list.services.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.project.Game.list.services.exceptions.GameNotFoundException;
import com.project.Game.list.services.exceptions.IntegrityViolationException;

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
    @ExceptionHandler(IntegrityViolationException.class)
    public ResponseEntity<StandardError> integrityViolation(IntegrityViolationException e, HttpServletRequest request){
        String error = "Game cannot be deleted";
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<StandardError> UserNotFound(UserNotFoundException e, HttpServletRequest request){
        String error = "User not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> illegalArgumentException(IllegalArgumentException e, HttpServletRequest request){
        String error = "Operation not completed";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
