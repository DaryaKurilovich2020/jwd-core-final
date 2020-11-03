package com.epam.jwd.core_final.domain;

import java.time.LocalDate;
import java.util.List;

/**
 * Expected fields:
 * <p>
 * missions name {@link String}
 * start date {@link java.time.LocalDate}
 * end date {@link java.time.LocalDate}
 * distance {@link Long} - missions distance
 * assignedSpaceShift {@link Spaceship} - not defined by default
 * assignedCrew {@link java.util.List<CrewMember>} - list of missions members based on ship capacity - not defined by default
 * missionResult {@link MissionResult}
 */
public class FlightMission extends AbstractBaseEntity {

    private LocalDate startDate;
    private LocalDate endDate;
    private long distance;
    private MissionResult missionResult;
    private Spaceship assignedSpaceShift;
    private List<CrewMember> assignedCrew;

    public FlightMission(Long id, String missionsName) {
        super(id, missionsName);
    }
    // todo
}
