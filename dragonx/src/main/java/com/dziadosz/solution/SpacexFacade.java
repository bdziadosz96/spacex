package com.dziadosz.solution;

import lombok.RequiredArgsConstructor;
import java.util.UUID;

@RequiredArgsConstructor
public class SpacexFacade {
    
    private final RocketStorage rocketStorage;
    private final MissionStorage missionStorage;
    
    Rocket addRocket(String name) {
        Rocket rocket = Rocket.create(name);
        rocketStorage.add(rocket);
        return rocket;
    }
    
    Mission addMission(String name) {
        Mission mission = Mission.create(name);
        missionStorage.add(mission);
        return mission;
    }
    
    void assignRocketToMission(UUID rocketId, UUID missionId) {
        Rocket rocket = rocketStorage.findById(rocketId)
                .orElseThrow(() -> new RocketNotFoundException(rocketId));
        Mission mission = missionStorage.findById(missionId)
                .orElseThrow(() -> new MissionNotFoundException(missionId));
        
        rocket.assignToMission(missionId);
        mission.assignRocket(rocketId);
    }


}
