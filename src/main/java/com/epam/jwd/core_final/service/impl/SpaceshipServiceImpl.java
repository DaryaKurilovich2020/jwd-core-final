package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.AssignException;
import com.epam.jwd.core_final.exception.DuplicateException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.SpaceShipFactory;
import com.epam.jwd.core_final.service.SpaceshipService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SpaceshipServiceImpl implements SpaceshipService {
    private static SpaceshipServiceImpl spaceshipService;
    private static NassaContext nassaContext;

    public static void setNassaContext(NassaContext nassaContext) {
        SpaceshipServiceImpl.nassaContext = nassaContext;
    }

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
        return nassaContext.retrieveBaseEntityList(Spaceship.class)
                .stream()
                .collect(Collectors.toList());
    }

    //todo
    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        return nassaContext.retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(spaceship -> spaceship.equals(criteria.build().getName()))

    }

    //todo
    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        return nassaContext.retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(spaceship -> spaceship.getName().equals(criteria.build().getName()))
                .
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        Collection<Spaceship> spaceships = nassaContext.retrieveBaseEntityList(Spaceship.class);
        Optional<Spaceship> spaceshipToUpdate = spaceships.stream()
                .filter(spaceship1 -> spaceship1.getName().equals(spaceship.getName()))
                .findAny();
        if (spaceshipToUpdate.isPresent()) {
            spaceships.remove(spaceshipToUpdate.get());
            spaceships.add(spaceship);
        }
        return spaceship;
    }

    @Override
    public void assignSpaceshipOnMission(Spaceship spaceship, FlightMission flightMission) throws AssignException {
        if (spaceship.isReadyForNextMissions()) {
            flightMission.setAssignedSpaceShip(spaceship);
        } else {
            throw new AssignException("This spaceship is not ready for a mission");
        }
    }

    @Override
    public Spaceship createSpaceship(Object... args) throws DuplicateException {
        if (isDuplicate((String) args[0])) {
            throw new DuplicateException("Spaceship " + args[0] + "already exists");
        }
        EntityFactory<Spaceship> factory = new SpaceShipFactory();
        return factory.create(args);
    }

    public static boolean isDuplicate(String name) {
        Collection<Spaceship> spaceships = nassaContext.retrieveBaseEntityList(Spaceship.class);
        Optional<Spaceship> duplicatedCrewMember = spaceships.stream()
                .filter(spaceship -> spaceship.getName().equals(name))
                .findAny();
        if (duplicatedCrewMember.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}