package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.AssignException;
import com.epam.jwd.core_final.exception.DuplicateException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.CrewService;

import java.util.List;
import java.util.Optional;

public class CrewServiceImpl implements CrewService {
    private static CrewServiceImpl crewService;

    private CrewServiceImpl() {
    }

    public static CrewServiceImpl getInstance() {
        if (crewService == null) {
            crewService = new CrewServiceImpl();
        }
        return crewService;
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return null;
    }

    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        return null;
    }

    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        return Optional.empty();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        return null;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember, FlightMission flightMission) throws AssignException {
        flightMission.getAssignedCrew().add(crewMember);
    }

    @Override
    public CrewMember createCrewMember(Object... args) throws DuplicateException {
        if (isDuplicate((String) args[0])) {
            throw new DuplicateException("CrewMember " + args[0] + "already exists");
        }
        EntityFactory<CrewMember> factory = new CrewMemberFactory();
        return factory.create(args);
    }

    public static boolean isDuplicate(String name) {
        NassaContext nassaContext = NassaContext.getInstance();
        List<CrewMember> crewMembers = nassaContext.retrieveBaseEntityList();
        CrewMemberCriteria crewMemberCriteria = new CrewMemberCriteria();
        crewMemberCriteria.name(name).build();
        Optional<CrewMember> duplicatedCrewMember = crewMembers.stream()
                .filter(crewMember -> crewMember.getName().equals(name))
                .findAny();
        if (duplicatedCrewMember.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    public static void checkArguments(Object... args) {
        boolean invalidArguments = false;
        if (args.length > 2) {
            invalidArguments = args[0] instanceof String ? false : true;
            invalidArguments = args[1] instanceof Role ? false : true;
            invalidArguments = args[2] instanceof Rank ? false : true;
        }
        if (invalidArguments) {
            throw new IllegalArgumentException("Invalid set of arguments");
        }
    }
}
