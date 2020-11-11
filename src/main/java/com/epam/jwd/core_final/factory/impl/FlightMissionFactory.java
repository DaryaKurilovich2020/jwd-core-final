package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.MissionResult;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.util.IdCounter;

import java.time.LocalDateTime;
import java.util.List;

public class FlightMissionFactory implements EntityFactory<FlightMission> {
    @Override
    public FlightMission create(Object... args) {
        FlightMission newFlightMission = new FlightMission((Long) IdCounter.getId(), (String) args[0]);
        newFlightMission.setStartDate((LocalDateTime) args[1]);
        newFlightMission.setEndDate((LocalDateTime)args[2]);
        newFlightMission.setDistance((long) args[3]);
        newFlightMission.setMissionResult(MissionResult.PLANNED);
        newFlightMission.setAssignedSpaceShip((Spaceship) args[5]);
        newFlightMission.setAssignedCrew((List<CrewMember>) args[4]);
        NassaContext.getInstance().retrieveBaseEntityList(FlightMission.class).add(newFlightMission);
        return newFlightMission;
    }
}
