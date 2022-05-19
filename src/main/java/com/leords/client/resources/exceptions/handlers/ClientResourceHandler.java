package com.leords.client.resources.exceptions.handlers;

import com.leords.client.resources.exceptions.StandardError;
import com.leords.client.services.exceptions.ClientNotFoundException;
import com.leords.client.services.exceptions.DatabaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ClientResourceHandler {
    
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<StandardError> clientNotFound(ClientNotFoundException exception, HttpServletRequest request) {
        var status = HttpStatus.NOT_FOUND;
        var error = new StandardError();
        
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Client not found");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        
        return ResponseEntity.status(status).body(error);
    }
    
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseIntegrityViolation(ClientNotFoundException exception, HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var error = new StandardError();
        
        error.setTimestamp(Instant.now());
        error.setStatus(status.value());
        error.setError("Database violation");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        
        return ResponseEntity.status(status).body(error);
    }
    
}
