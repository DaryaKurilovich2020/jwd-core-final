package com.epam.jwd.core_final.domain;

import java.util.Map;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {

    private long flightDistance;
    private Map<Role, Short> crew;
    private boolean isReadyForNextMissions;

    public Spaceship(Long id, String name) {
        super(id, name);
    }

    public void setFlightDistance(long flightDistance) {
        this.flightDistance = flightDistance;
    }

    public void setCrew(Map<Role, Short> crew) {
        this.crew = crew;
    }

    public void setReadyForNextMissions(boolean readyForNextMissions) {
        isReadyForNextMissions = readyForNextMissions;
    }

    public long getFlightDistance() {
        return flightDistance;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    public boolean isReadyForNextMissions() {
        return isReadyForNextMissions;
    }
}