package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.util.IdCounter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {

    @Override
    public CrewMember create(Object... args) {
        CrewMember newCrewMember = new CrewMember((Long) IdCounter.getId(), (String) args[0]);
        newCrewMember.setRole((Role) args[1]);
        newCrewMember.setRank((Rank) args[2]);
        newCrewMember.setReadyForNextMission(true);
        return newCrewMember;
    }
}