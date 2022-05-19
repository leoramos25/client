package com.leords.client.services.exceptions;

public class DatabaseException extends RuntimeException {
    
    private static final long serialVersionUID = -708209824728434056L;
    
    public DatabaseException(String message) {
        super(message);
    }
    
}
