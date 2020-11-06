package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {

    @Override
    public CrewMember create(Object... args) {
        CrewMember newCrewMember = new CrewMember((Long) args[0], (String) args[1]);
        newCrewMember.setRole((Role) args[2]);
        newCrewMember.setRank((Rank) args[3]);
        newCrewMember.setReadyForNextMission(true);
        return newCrewMember;
    }
}