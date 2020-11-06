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
        FlightMission newFlightMission = new FlightMission((Long) args[0], (String) args[1]);
        newFlightMission.setStartDate((LocalDate) args[2]);
        newFlightMission.setDistance((long) args[3]);
        newFlightMission.setMissionResult(MissionResult.PLANNED);
        newFlightMission.setAssignedCrew((List<CrewMember>) args[4]);
        newFlightMission.setAssignedSpaceShip((Spaceship) args[5]);
        return newFlightMission;
    }
}
