package com.dziadosz.solution;

import java.util.UUID;

public class RocketNotFoundException extends RuntimeException {
    public RocketNotFoundException(UUID rocketId) {
        super("Rocket not found with id: " + rocketId);
    }
}