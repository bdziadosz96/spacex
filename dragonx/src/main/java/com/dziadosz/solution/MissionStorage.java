package com.dziadosz.solution;

import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import java.util.Optional;

@NoArgsConstructor
class MissionStorage {
    private final Map<UUID, Mission> missions = new HashMap<>();
    
    void add(Mission mission) {
        missions.put(mission.getId(), mission);
    }
    
    Optional<Mission> findById(UUID id) {
        return Optional.ofNullable(missions.get(id));
    }
}