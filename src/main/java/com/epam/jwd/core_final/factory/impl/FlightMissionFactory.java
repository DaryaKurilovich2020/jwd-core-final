package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.util.IdCounter;

import java.time.LocalDate;
import java.util.List;

public class FlightMissionFactory implements EntityFactory<FlightMission> {
    @Override
    public FlightMission create(Object... args) {
        FlightMission newFlightMission = new FlightMission((Long) IdCounter.getId(), (String) args[0]);
        newFlightMission.setStartDate((LocalDate) args[1]);
        newFlightMission.setDistance((long) args[2]);
        newFlightMission.setMissionResult(MissionResult.PLANNED);
        newFlightMission.setAssignedCrew((List<CrewMember>) args[3]);
        newFlightMission.setAssignedSpaceShip((Spaceship) args[3]);
        return newFlightMission;
    }
}
