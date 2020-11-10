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
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
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
        return nassaContext.retrieveBaseEntityList(FlightMission.class)
                .stream()
                .collect(Collectors.toList());
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
            predicate = criteria.build().getName().equals(flightMission.getName()) ? true : false;
        }
        if (criteria.build().getId() != null) {
            predicate = criteria.build().getId().equals(flightMission.getId()) ? true : false;
        }
        if (criteria.build().getDistance() != 0) {
            predicate = criteria.build().getDistance() == flightMission.getDistance() ? true : false;
        }
        if (criteria.build().getStartDate() != null) {
            predicate = criteria.build().getStartDate().equals(flightMission.getStartDate()) ? true : false;
        }
        if (criteria.build().getEndDate() != null) {
            predicate = criteria.build().getEndDate().equals(flightMission.getEndDate()) ? true : false;
        }
        if (criteria.build().getMissionResult() != null) {
            predicate = criteria.build().getMissionResult().equals(flightMission.getMissionResult()) ? true : false;
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
        if (duplicatedMission.isPresent()) {
            return true;
        } else {
            return false;
        }
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
                case 0:
                    result = MissionResult.CANCELLED;
                    break;
                case 1:
                    result = MissionResult.COMPLETED;
                    break;
                case 2:
                    result = MissionResult.FAILED;
                    failMission(flightMission);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + randomNum);
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
        crewMembers.stream()
                .forEach(crewMember -> crewMember.setReadyForNextMission(false));
        flightMission.getAssignedSpaceShip().setReadyForNextMissions(false);
    }
}