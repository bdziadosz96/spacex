package com.dziadosz.solution;

import java.util.UUID;

public class MissionNotFoundException extends RuntimeException {
    public MissionNotFoundException(UUID missionId) {
        super("Mission not found with id: " + missionId);
    }
}