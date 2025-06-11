package com.dziadosz.solution;

public class RocketAlreadyAssignedException extends RuntimeException {
    
    public RocketAlreadyAssignedException(String rocketName) {
        super("Rocket '" + rocketName + "' is already assigned to a mission");
    }
}