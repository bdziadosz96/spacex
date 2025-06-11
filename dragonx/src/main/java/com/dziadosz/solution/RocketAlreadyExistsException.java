package com.dziadosz.solution;

public class RocketAlreadyExistsException extends RuntimeException {
    
    public RocketAlreadyExistsException(String rocketName) {
        super("Rocket with name '" + rocketName + "' already exists");
    }
}