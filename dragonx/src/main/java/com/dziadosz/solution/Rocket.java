package com.dziadosz.solution;

import lombok.Getter;
import java.util.UUID;

@Getter
class Rocket {

    private final UUID id;
    private final String name;
    private final Status status;
    private UUID missionId;
    
    private Rocket(UUID id, String name, Status status) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.missionId = null;
    }
    
    static Rocket create(String name) {
        return new Rocket(UUID.randomUUID(), name, Status.ON_GROUND);
    }
    
    void assignToMission(UUID missionId) {
        if (this.missionId != null) {
            throw new RocketAlreadyAssignedException(this.name);
        }
        this.missionId = missionId;
    }

    enum Status {
        ON_GROUND,
        IN_SPACE,
        IN_REPAIR,
        IN_BUILD
    }
}
