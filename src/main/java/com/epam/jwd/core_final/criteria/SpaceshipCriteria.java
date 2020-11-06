package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.SpaceShipFactory;

import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private long flightDistance;
    private Map<Role, Short> crew;

    @Override
    public SpaceshipCriteria id(Long id) {
        return (SpaceshipCriteria) super.id(id);
    }

    @Override
    public SpaceshipCriteria name(String name) {
        return (SpaceshipCriteria) super.name(name);
    }

    public SpaceshipCriteria flightDistance(long flightDistance) {
        this.flightDistance = flightDistance;
        return this;
    }

    public SpaceshipCriteria crew(Map<Role, Short> crew) {
        this.crew = crew;
        return this;
    }

    @Override
    public Spaceship build() {
        EntityFactory<Spaceship> spaceshipFactory = new SpaceShipFactory();
        return spaceshipFactory.create(this.id, this.name, this.flightDistance, this.crew);
    }
}
