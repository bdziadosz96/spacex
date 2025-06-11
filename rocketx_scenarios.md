# Rocket Management Scenarios

## Scenario 1: Adding a new rocket
**Given** new rocket "Dragon Heavy"  
**When** rocket is added  
**Then** status is "On ground"

## Scenario 2: Adding rocket with duplicate name
**Given** existing rocket "Dragon Heavy"  
**When** another rocket "Dragon Heavy" is added  
**Then** RocketAlreadyExistsException is thrown

## Scenario 3: Assigning rocket to mission
**Given** rocket "Falcon 9" and mission "Mars Landing"  
**When** rocket is assigned to mission  
**Then** rocket has mission assigned

## Scenario 4: Rocket can only be assigned to one mission
**Given** rocket "Falcon Heavy" assigned to mission "Moon Base"  
**When** rocket is assigned to another mission "Mars Colony"  
**Then** RocketAlreadyAssignedException is thrown

## Scenario 5: Adding a new mission
**Given** new mission "ISS Resupply"  
**When** mission is added  
**Then** status is "SCHEDULED"

## Scenario 6: Mission can have multiple rockets
**Given** mission "Mars Colony" and rockets "Dragon 1", "Dragon 2", "Dragon 3"  
**When** all rockets are assigned to the mission  
**Then** mission has 3 rockets assigned  

## Scenario 7: Assigning non-existent rocket to mission
**Given** non-existent rocket ID  
**When** rocket is assigned to mission  
**Then** RocketNotFoundException is thrown

## Scenario 8: Assigning rocket to non-existent mission
**Given** existing rocket "Falcon 9" and non-existent mission ID  
**When** rocket is assigned to mission  
**Then** MissionNotFoundException is thrown



Assumptions:
1. No validation: There's no validation for:
   - Empty or null rocket names
   - Maximum length of rocket names
   - Special characters in names
2. No concurrency concerns: The implementation is not thread-safe, assuming single-threaded usage