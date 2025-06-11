package com.dziadosz.solution;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SpacexApplicationTest {

    RocketStorage rocketStorage;
    MissionStorage missionStorage;
    SpacexFacade facade;

    @BeforeEach
    void setUp() {
        rocketStorage = new RocketStorage();
        missionStorage = new MissionStorage();
        facade = new SpacexFacade(rocketStorage, missionStorage);
    }
    
    @Test
    @DisplayName("Given a new rocket 'Mars', when a rocket is added, then the rocket should have status 'On ground'")
    void shouldAddNewRocketWithOnGroundStatus() {
        // Given
        var mars = "Mars";

        // When
        var rocket = facade.addRocket(mars);
        
        // Then
        assertThat(rocket).isNotNull();
        assertThat(rocket.getStatus()).isEqualTo(Rocket.Status.ON_GROUND);
        assertThat(rocket.getName()).isEqualTo(mars);
    }
    
    @Test
    @DisplayName("Given existing rocket 'Dragon Heavy', when another rocket 'Dragon Heavy' is added, then RocketAlreadyExistsException is thrown")
    void shouldThrowExceptionWhenAddingRocketWithDuplicateName() {
        // Given
        var dragonHeavy = "Dragon Heavy";
        facade.addRocket(dragonHeavy);
        
        // When & Then
        assertThatThrownBy(() -> facade.addRocket(dragonHeavy))
                .isInstanceOf(RocketAlreadyExistsException.class)
                .hasMessage("Rocket with name 'Dragon Heavy' already exists");
    }
    
    @Test
    @DisplayName("Given new mission 'ISS Resupply', when mission is added, then status is 'SCHEDULED'")
    void shouldAddNewMissionWithScheduledStatus() {
        // Given
        var missionName = "ISS Resupply";
        
        // When
        var mission = facade.addMission(missionName);
        
        // Then
        assertThat(mission).isNotNull();
        assertThat(mission.getStatus()).isEqualTo(Mission.Status.SCHEDULED);
        assertThat(mission.getName()).isEqualTo(missionName);
    }
    
    @Test
    @DisplayName("Given rocket 'Falcon 9' and mission 'Mars Landing', when rocket is assigned to mission, then rocket has mission assigned")
    void shouldAssignRocketToMission() {
        // Given
        var rocket = facade.addRocket("Falcon 9");
        var mission = facade.addMission("Mars Landing");
        
        // When
        facade.assignRocketToMission(rocket.getId(), mission.getId());
        
        // Then
        assertThat(rocket.getMissionId()).isEqualTo(mission.getId());
        assertThat(mission.getRocketIds()).contains(rocket.getId());
    }
    
    @Test
    @DisplayName("Given rocket 'Falcon Heavy' assigned to mission 'Moon Base', when rocket is assigned to another mission 'Mars Colony', then RocketAlreadyAssignedException is thrown")
    void shouldThrowExceptionWhenAssigningRocketToSecondMission() {
        // Given
        var rocket = facade.addRocket("Falcon Heavy");
        var mission1 = facade.addMission("Moon Base");
        var mission2 = facade.addMission("Mars Colony");
        facade.assignRocketToMission(rocket.getId(), mission1.getId());
        
        // When & Then
        assertThatThrownBy(() -> facade.assignRocketToMission(rocket.getId(), mission2.getId()))
                .isInstanceOf(RocketAlreadyAssignedException.class)
                .hasMessage("Rocket 'Falcon Heavy' is already assigned to a mission");
    }
    
    @Test
    @DisplayName("Given mission 'Mars Colony' and rockets 'Dragon 1', 'Dragon 2', 'Dragon 3', when all rockets are assigned to the mission, then mission has 3 rockets assigned")
    void shouldAssignMultipleRocketsToMission() {
        // Given
        var mission = facade.addMission("Mars Colony");
        var rocket1 = facade.addRocket("Dragon 1");
        var rocket2 = facade.addRocket("Dragon 2");
        var rocket3 = facade.addRocket("Dragon 3");
        
        // When
        facade.assignRocketToMission(rocket1.getId(), mission.getId());
        facade.assignRocketToMission(rocket2.getId(), mission.getId());
        facade.assignRocketToMission(rocket3.getId(), mission.getId());
        
        // Then
        assertThat(mission.getRocketIds()).hasSize(3);
        assertThat(mission.getRocketIds()).containsExactlyInAnyOrder(
                rocket1.getId(), rocket2.getId(), rocket3.getId()
        );
    }
    
    @Test
    @DisplayName("Given non-existent rocket ID, when assigning rocket to mission, then RocketNotFoundException is thrown")
    void shouldThrowRocketNotFoundExceptionForNonExistentRocket() {
        // Given
        var nonExistentRocketId = UUID.randomUUID();
        var mission = facade.addMission("Mars Mission");
        
        // When & Then
        assertThatThrownBy(() -> facade.assignRocketToMission(nonExistentRocketId, mission.getId()))
                .isInstanceOf(RocketNotFoundException.class)
                .hasMessage("Rocket not found with id: " + nonExistentRocketId);
    }
    
    @Test
    @DisplayName("Given non-existent mission ID, when assigning rocket to mission, then MissionNotFoundException is thrown")
    void shouldThrowMissionNotFoundExceptionForNonExistentMission() {
        // Given
        var rocket = facade.addRocket("Falcon 9");
        var nonExistentMissionId = UUID.randomUUID();
        
        // When & Then
        assertThatThrownBy(() -> facade.assignRocketToMission(rocket.getId(), nonExistentMissionId))
                .isInstanceOf(MissionNotFoundException.class)
                .hasMessage("Mission not found with id: " + nonExistentMissionId);
    }
}
