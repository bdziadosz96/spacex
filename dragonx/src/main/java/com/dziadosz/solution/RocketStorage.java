package com.dziadosz.solution;

import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import java.util.Optional;

@NoArgsConstructor
class RocketStorage {
    private final Map<UUID, Rocket> rockets = new HashMap<>();
    
    void add(Rocket rocket) {
        if (rocketWithNameExist(rocket.getName())) {
            throw new RocketAlreadyExistsException(rocket.getName());
        }
        rockets.put(rocket.getId(), rocket);
    }
    
    private boolean rocketWithNameExist(String name) {
        return rockets.values().stream()
                .anyMatch(rocket -> rocket.getName().equals(name));
    }
    
    Optional<Rocket> findById(UUID id) {
        return Optional.ofNullable(rockets.get(id));
    }
}