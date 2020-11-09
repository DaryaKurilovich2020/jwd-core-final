package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.util.IdCounter;

import java.util.Map;

public class SpaceShipFactory implements EntityFactory<Spaceship> {
    @Override
    public Spaceship create(Object... args) {
        Spaceship newSpaceship = new Spaceship((Long) IdCounter.getId(), (String) args[0]);
        newSpaceship.setFlightDistance((long) args[1]);
        newSpaceship.setCrew((Map<Role, Short>) args[2]);
        newSpaceship.setReadyForNextMissions(true);
        return newSpaceship;
    }
}
