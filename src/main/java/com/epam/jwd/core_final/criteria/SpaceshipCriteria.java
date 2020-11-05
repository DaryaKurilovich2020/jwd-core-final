package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.domain.Spaceship;

import java.util.Map;

/**
 * Should be a builder for {@link Spaceship} fields
 */
public class SpaceshipCriteria extends Criteria<Spaceship> {
    private final long flightDistance;
    private final Map<Role, Short> crew;

    public long getFlightDistance() {
        return flightDistance;
    }

    public Map<Role, Short> getCrew() {
        return crew;
    }

    private SpaceshipCriteria(Long id, String name, long flightDistance, Map<Role, Short> crew) {
        super(id, name);
        this.flightDistance = flightDistance;
        this.crew = crew;
    }

    private class SpaceShipCriteriaBuilder extends CriteriaBuilder {
        private long flightDistance;
        private Map<Role, Short> crew;

        public SpaceShipCriteriaBuilder() {
        }

        public SpaceShipCriteriaBuilder flightDistance(long flightDistance) {
            this.flightDistance = flightDistance;
            return this;
        }

        public SpaceShipCriteriaBuilder crew(Map<Role, Short> crew) {
            this.crew = crew;
            return this;
        }

        @Override
        public SpaceshipCriteria build() {
            return new SpaceshipCriteria(id, name, flightDistance, crew);
        }
    }
}
