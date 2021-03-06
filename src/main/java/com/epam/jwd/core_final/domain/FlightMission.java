package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.service.impl.MissionsServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.StringJoiner;

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

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private long distance;
    private MissionResult missionResult;
    private Spaceship assignedSpaceShip;
    private List<CrewMember> assignedCrew;
    private int resultCallCounter;

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public long getDistance() {
        return distance;
    }

    public MissionResult getMissionResult() {
        return MissionsServiceImpl.getMissionResult(this);
    }

    public MissionResult result() {
        return this.missionResult;
    }

    public int getResultCallCounter() {
        return resultCallCounter;
    }

    public void setResultCallCounter(int resultCallCounter) {
        this.resultCallCounter = resultCallCounter;
    }

    public Spaceship getAssignedSpaceShip() {
        return assignedSpaceShip;
    }

    public List<CrewMember> getAssignedCrew() {
        return assignedCrew;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner=new StringJoiner("\n");
        stringJoiner.add(super.toString());
        stringJoiner.add("Start date "+this.getStartDate());
        stringJoiner.add("End date "+this.getEndDate());
        stringJoiner.add("Spaceship "+this.getAssignedSpaceShip().getName());
        return stringJoiner.toString();
    }
}
