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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return new ArrayList<>(NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class));
    }

    public List<Spaceship> findAllAvailableShips() {
        return NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(spaceship -> spaceship.isReadyForNextMissions())
                .collect(Collectors.toList());
    }

    @Override
    public List<Spaceship> findAllSpaceshipsByCriteria(Criteria<? extends Spaceship> criteria) {
        return NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(spaceship -> criteriaPredicate(spaceship, criteria))
                .collect(Collectors.toList());
    }

    private static boolean criteriaPredicate(Spaceship spaceship, Criteria<? extends Spaceship> criteria) {
        boolean predicate = true;
        if (criteria.build().getName() != null) {
            predicate = criteria.build().getName().equals(spaceship.getName());
        }
        if (criteria.build().getId() != null) {
            predicate = criteria.build().getId().equals(spaceship.getId());
        }
        if (criteria.build().getFlightDistance() != 0) {
            predicate = criteria.build().getFlightDistance() >= (spaceship.getFlightDistance());
        }
        return predicate;
    }

    @Override
    public Optional<Spaceship> findSpaceshipByCriteria(Criteria<? extends Spaceship> criteria) {
        return NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class)
                .stream()
                .filter(spaceship -> criteriaPredicate(spaceship, criteria))
                .findFirst();
    }

    @Override
    public Spaceship updateSpaceshipDetails(Spaceship spaceship) {
        Collection<Spaceship> spaceships = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class);
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
        if (isDuplicate((String) args[1])) {
            throw new DuplicateException("Spaceship " + args[1] + "already exists");
        }
        EntityFactory<Spaceship> factory = new SpaceShipFactory();
        return factory.create(args);
    }

    public static boolean isDuplicate(String name) {
        Collection<Spaceship> spaceships = NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class);
        Optional<Spaceship> duplicatedCrewMember = spaceships.stream()
                .filter(spaceship -> spaceship.getName().equals(name))
                .findAny();
        return duplicatedCrewMember.isPresent();
    }
}