package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;

import java.time.LocalDate;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private LocalDate startDate;
    private LocalDate endDate;
    private long distance;
    private MissionResult missionResult;
    private Spaceship spaceship;
    private List<CrewMember> crewMemberList;

    public FlightMissionCriteria startDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public FlightMissionCriteria endDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public FlightMissionCriteria distance(long distance) {
        this.distance = distance;
        return this;
    }

    public FlightMissionCriteria missionResult(MissionResult missionResult) {
        this.missionResult = missionResult;
        return this;
    }

    public FlightMissionCriteria spaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
        return this;
    }

    public FlightMissionCriteria crewMemberList(List<CrewMember> crewMemberList) {
        this.crewMemberList = crewMemberList;
        return this;
    }

    @Override
    public FlightMissionCriteria id(Long id) {
        return (FlightMissionCriteria) super.id(id);
    }

    @Override
    public FlightMissionCriteria name(String name) {
        return (FlightMissionCriteria) super.name(name);
    }

    @Override
    public FlightMission build() {
        EntityFactory<FlightMission> flightMissionFactory = new FlightMissionFactory();
        return flightMissionFactory.create(this.id, this.name, this.startDate, this.endDate, this.distance, this.crewMemberList, this.spaceship);
    }
}