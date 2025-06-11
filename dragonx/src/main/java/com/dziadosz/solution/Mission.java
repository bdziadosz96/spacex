package com.dziadosz.solution;

import lombok.Getter;
import java.util.UUID;
import java.util.Set;
import java.util.HashSet;

@Getter
class Mission {
    
    enum Status {
        SCHEDULED,
        PENDING,
        IN_PROGRESS,
        ENDED
    }
    
    private final UUID id;
    private final String name;
    private final Set<UUID> rocketIds = new HashSet<>();
    private final Status status;
    
    private Mission(UUID id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }
    
    static Mission create(String name) {
        return new Mission(UUID.randomUUID(), name, Status.SCHEDULED);
    }
    
    void assignRocket(UUID rocketId) {
        rocketIds.add(rocketId);
    }
}