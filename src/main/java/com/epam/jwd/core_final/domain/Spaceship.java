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
    private boolean isReadyForNextMissions = true;

    public Spaceship(Long id, String name, long flightDistance, Map<Role, Short> crew) {
        super(id, name);
        this.flightDistance = flightDistance;
        this.crew = crew;
    }
}