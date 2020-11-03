package com.epam.jwd.core_final.domain;

import java.util.Map;

/**
 * crew {@link java.util.Map<Role, Short>}
 * flightDistance {@link Long} - total available flight distance
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class Spaceship extends AbstractBaseEntity {
    //todo
    private long flightDistance;
    private Map<Role, Short> crew;
    private boolean isReadyForNextMissions = true;

    public Spaceship(Long id, String name) {
        super(id, name);
    }
}
