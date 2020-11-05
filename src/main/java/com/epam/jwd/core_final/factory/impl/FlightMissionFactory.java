package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;

import java.time.LocalDate;
import java.util.List;

public class FlightMissionFactory implements EntityFactory<FlightMission> {
    @Override
    public FlightMission create(Object... args) {
        Object[] crewArgs = args;
        return new FlightMission((Long) args[0], (String) args[1], (LocalDate) args[2], (LocalDate) args[3], (long) args[4], (MissionResult) args[5], (Spaceship) args[6], (List<CrewMember>) args[7]);
    }
}
