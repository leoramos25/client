package com.leords.client.services.exceptions;

public class ClientNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = 2479757102581899233L;
    
    public ClientNotFoundException(String message) {
        super(message);
    }
}
