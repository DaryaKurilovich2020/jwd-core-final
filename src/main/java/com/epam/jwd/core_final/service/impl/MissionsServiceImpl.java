package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.DuplicateException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.FlightMissionFactory;
import com.epam.jwd.core_final.service.MissionService;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class MissionsServiceImpl implements MissionService {
    private static MissionsServiceImpl missionsService;
    private static NassaContext nassaContext;

    public static void setNassaContext(NassaContext nassaContext) {
        MissionsServiceImpl.nassaContext = nassaContext;
    }

    private MissionsServiceImpl() {

    }

    public static MissionsServiceImpl getInstance() {
        if (missionsService == null) {
            missionsService = new MissionsServiceImpl();
        }
        return missionsService;
    }

    @Override
    public List<FlightMission> findAllMissions() {
        return new ArrayList<>(nassaContext.retrieveBaseEntityList(FlightMission.class));
    }

    @Override
    public List<FlightMission> findAllMissionsByCriteria(Criteria<? extends FlightMission> criteria) {
        return nassaContext.retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(flightMission -> criteriaPredicate(flightMission, criteria))
                .collect(Collectors.toList());
    }

    private static boolean criteriaPredicate(FlightMission flightMission, Criteria<? extends FlightMission> criteria) {
        boolean predicate = true;
        if (criteria.build().getName() != null) {
            predicate = Objects.equals(criteria.build().getName(), flightMission.getName());
        }
        if (criteria.build().getId() != null) {
            predicate = Objects.equals(criteria.build().getId(), flightMission.getId());
        }
        if (criteria.build().getDistance() != 0) {
            predicate = criteria.build().getDistance() == flightMission.getDistance();
        }
        if (criteria.build().getStartDate() != null) {
            predicate = criteria.build().getStartDate().equals(flightMission.getStartDate());
        }
        if (criteria.build().getEndDate() != null) {
            predicate = criteria.build().getEndDate().equals(flightMission.getEndDate());
        }
        if (criteria.build().getMissionResult() != null) {
            predicate = Objects.equals(criteria.build().getMissionResult(), flightMission.getMissionResult());
        }
        return predicate;
    }

    @Override
    public Optional<FlightMission> findMissionByCriteria(Criteria<? extends FlightMission> criteria) {
        return nassaContext.retrieveBaseEntityList(FlightMission.class)
                .stream()
                .filter(flightMission -> criteriaPredicate(flightMission, criteria))
                .findFirst();
    }

    @Override
    public FlightMission updateMissionDetails(FlightMission flightMission) {
        Collection<FlightMission> flightMissions = nassaContext.retrieveBaseEntityList(FlightMission.class);
        Optional<FlightMission> flightMissionToUpdate = flightMissions
                .stream()
                .filter(flightMission1 -> flightMission1.getName().equals(flightMission.getName()))
                .findAny();
        if (flightMissionToUpdate.isPresent()) {
            flightMissions.remove(flightMissionToUpdate.get());
            flightMissions.add(flightMission);
        }
        return flightMission;
    }

    @Override
    public FlightMission createMission(Object... args) throws DuplicateException {
        checkArgs(args);
        if (isDuplicate((String) args[0])) {
            throw new DuplicateException("Mission " + args[0] + "already exists");
        }
        EntityFactory<FlightMission> factory = new FlightMissionFactory();
        FlightMission flightMission = factory.create(args);
        return flightMission;
    }

    public static boolean isDuplicate(String name) {
        Collection<FlightMission> missions = nassaContext.retrieveBaseEntityList(FlightMission.class);
        Optional<FlightMission> duplicatedMission = missions.stream()
                .filter(mission -> mission.getName().equals(name))
                .findAny();
        return duplicatedMission.isPresent();
    }

    private static void checkArgs(Object... args) {
        boolean invalidArgs = false;
        if (args.length == 6) {
            if (!(args[0] instanceof String)) {
                invalidArgs = true;
            }
            if (!(args[1] instanceof LocalDateTime)) {
                invalidArgs = true;
            }
            if (!(args[2] instanceof LocalDateTime)) {
                invalidArgs = true;
            }
            if (!(args[3] instanceof Long)) {
                invalidArgs = true;
            }
            if (!(args[4] instanceof List)) {
                invalidArgs = true;
            }
            if (!(args[5] instanceof Spaceship)) {
                invalidArgs = true;
            }
        } else {
            invalidArgs = true;
        }
        if (invalidArgs) {
            throw new IllegalArgumentException("Invalid set of arguments");
        }
    }

    public static MissionResult getMissionResult(FlightMission flightMission) {
        int resultCallCounter = flightMission.getResultCallCounter();
        flightMission.setResultCallCounter(resultCallCounter++);
        if (resultCallCounter == 0) {
            flightMission.setMissionResult(MissionResult.PLANNED);
            return MissionResult.PLANNED;
        }
        if (resultCallCounter == 1) {
            flightMission.setMissionResult(MissionResult.IN_PROGRESS);
            return MissionResult.IN_PROGRESS;
        }
        if (resultCallCounter == 2) {
            int randomNum = ThreadLocalRandom.current().nextInt(0, 3);
            MissionResult result;
            switch (randomNum) {
                case 0 -> result = MissionResult.CANCELLED;
                case 1 -> result = MissionResult.COMPLETED;
                case 2 -> {
                    result = MissionResult.FAILED;
                    failMission(flightMission);
                }
                default -> throw new IllegalStateException("Unexpected value: " + randomNum);
            }
            flightMission.setMissionResult(result);
            return result;
        }
        if (resultCallCounter > 2) {
            return flightMission.result();
        }
        return MissionResult.PLANNED;
    }

    private static void failMission(FlightMission flightMission) {
        List<CrewMember> crewMembers = flightMission.getAssignedCrew();
        for (CrewMember crewMember : crewMembers) {
            crewMember.setReadyForNextMission(false);
        }
        flightMission.getAssignedSpaceShip().setReadyForNextMissions(false);
    }
}