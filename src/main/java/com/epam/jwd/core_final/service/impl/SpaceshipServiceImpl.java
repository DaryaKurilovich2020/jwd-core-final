package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.List;
import java.util.Optional;

public class SpaceshipServiceImpl implements SpaceshipService {
    private static SpaceshipServiceImpl spaceshipService;

    private SpaceshipServiceImpl() {

    }

    public static SpaceshipServiceImpl getInstance() {
        if (spaceshipService == null) {
            spaceshipService = new SpaceshipServiceImpl();
        }
        return spaceshipService;
    }

    @Override
    public List<Spaceship> findAllSpaceships() {
        return null;
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        return null;
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        return Optional.empty();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        return null;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship, FlightMission flightMission) throws RuntimeException {

        flightMission.setAssignedSpaceShip(spaceship);
    }

    @Override
    public Spaceship createSpaceship(Spaceship spaceship) throws RuntimeException {
        return null;
    }
}
