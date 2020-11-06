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
    private Spaceship assignedSpaceShip;
    private List<CrewMember> assignedCrew;

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public void setMissionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
    }

    public void setAssignedSpaceShip(Spaceship assignedSpaceShip) {
        this.assignedSpaceShip = assignedSpaceShip;
    }

    public void setAssignedCrew(List<CrewMember> assignedCrew) {
        this.assignedCrew = assignedCrew;
    }

    public FlightMission(Long id, String name) {
        super(id, name);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public long getDistance() {
        return distance;
    }

    public MissionResult getMissionResult() {
        return missionResult;
    }

    public Spaceship getAssignedSpaceShip() {
        return assignedSpaceShip;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }
}
