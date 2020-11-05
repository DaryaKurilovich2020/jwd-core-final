package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;

import java.time.LocalDate;
import java.util.List;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.FlightMission} fields
 */
public class FlightMissionCriteria extends Criteria<FlightMission> {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final long distance;
    private final MissionResult missionResult;
    private final Spaceship spaceship;
    private final List<CrewMember> crewMemberList;

    private FlightMissionCriteria(Long id, String name, LocalDate startDate, LocalDate endDate, long distance, MissionResult missionResult, Spaceship spaceship, List<CrewMember> crewMemberList) {
        super(id, name);
        this.startDate = startDate;
        this.endDate = endDate;
        this.distance = distance;
        this.missionResult = missionResult;
        this.spaceship = spaceship;
        this.crewMemberList = crewMemberList;
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

    public Spaceship getSpaceship() {
        return spaceship;
    }

    public List<CrewMember> getCrewMemberList() {
        return crewMemberList;
    }

    private static class FlightMissionCriteriaBuilder extends CriteriaBuilder{
        private LocalDate startDate;
        private LocalDate endDate;
        private long distance;
        private MissionResult missionResult;
        private Spaceship spaceship;
        private List<CrewMember> crewMemberList;

        public FlightMissionCriteriaBuilder startDate(LocalDate D) {
            this.startDate = startDate;
            return this;
        }

        public FlightMissionCriteriaBuilder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public FlightMissionCriteriaBuilder distance(long distance) {
            this.distance = distance;
            return this;
        }

        public FlightMissionCriteriaBuilder missionResult(MissionResult missionResult) {
            this.missionResult = missionResult;
            return this;
        }

        public FlightMissionCriteriaBuilder spaceship(Spaceship spaceship) {
            this.spaceship = spaceship;
            return this;
        }

        public FlightMissionCriteriaBuilder crewMemberList(List<CrewMember> crewMemberList) {
            this.crewMemberList = crewMemberList;
            return this;
        }
        @Override
        public FlightMissionCriteria build(){
            return new FlightMissionCriteria(id,name,startDate,endDate,distance,missionResult,spaceship,crewMemberList);
        }
    }
}