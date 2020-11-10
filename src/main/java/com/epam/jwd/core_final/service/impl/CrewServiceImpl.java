package com.epam.jwd.core_final.service.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.exception.AssignException;
import com.epam.jwd.core_final.exception.DuplicateException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.service.CrewService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CrewServiceImpl implements CrewService {
    private static CrewServiceImpl crewService;
    private static NassaContext nassaContext;

    private CrewServiceImpl() {
    }

    public static CrewServiceImpl getInstance() {
        if (crewService == null) {
            crewService = new CrewServiceImpl();
        }
        return crewService;
    }

    public static void setNassaContext(NassaContext nassaContext) {
        CrewServiceImpl.nassaContext = nassaContext;
    }

    @Override
    public List<CrewMember> findAllCrewMembers() {
        return nassaContext.retrieveBaseEntityList(CrewMember.class)
                .stream()
                .collect(Collectors.toList());
    }
//todo
    @Override
    public List<CrewMember> findAllCrewMembersByCriteria(Criteria<? extends CrewMember> criteria) {
        return nassaContext.retrieveBaseEntityList(CrewMember.class)
                .stream()
                .filter(crewMember -> crewMember.equals(criteria.build()))
    }
//todo
    @Override
    public Optional<CrewMember> findCrewMemberByCriteria(Criteria<? extends CrewMember> criteria) {
        return Optional.empty();
    }

    @Override
    public CrewMember updateCrewMemberDetails(CrewMember crewMember) {
        Collection<CrewMember> crewMembers = nassaContext.retrieveBaseEntityList(CrewMember.class);
        Optional<CrewMember> crewMemberToUpdate = crewMembers.stream()
                .filter(crewMember1 -> crewMember1.getName().equals(crewMember.getName()))
                .findAny();
        if (crewMemberToUpdate.isPresent()) {
            crewMembers.remove(crewMemberToUpdate.get());
            crewMembers.add(crewMember);
        }
        return crewMember;
    }

    @Override
    public void assignCrewMemberOnMission(CrewMember crewMember, FlightMission flightMission) throws AssignException {
        if (crewMember.isReadyForNextMission()) {
            flightMission.getAssignedCrew().add(crewMember);
        }
        else{
            throw new AssignException("This crewmember is not ready for a mission.");
        }
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
        Collection<CrewMember> crewMembers = nassaContext.retrieveBaseEntityList(CrewMember.class);
        Optional<CrewMember> duplicatedCrewMember = crewMembers.stream()
                .filter(crewMember -> crewMember.getName().equals(name))
                .findAny();
        if (duplicatedCrewMember.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
