package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.SpaceshipCriteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.exception.DuplicateException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionsServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Scanner;

public interface MissionCreator {
    Scanner scanner = ApplicationMenu.scanner;
    Logger LOGGER = LoggerFactory.getLogger(MissionCreator.class);

    static void createMission() {
        System.out.println("Enter mission name");
        String name = scanner.next();
        System.out.println("Enter start date");
        LocalDateTime start = LocalDateTime.parse(scanner.next());
        System.out.println("Enter end date");
        LocalDateTime end = LocalDateTime.parse(scanner.next());
        System.out.println("Enter distance");
        long distance = Integer.parseInt(scanner.next());
        System.out.println("Choose spaceship");
        SpaceshipCriteria criteria = new SpaceshipCriteria();
        criteria.flightDistance(distance);
        for (int j = 0; j < SpaceshipServiceImpl.getInstance().findAllSpaceshipsByCriteria(criteria).size(); j++) {
            System.out.println(j + 1 + SpaceshipServiceImpl.getInstance().findAllSpaceshipsByCriteria(criteria).get(j).getName());
        }
        int i = scanner.nextInt();
        Spaceship spaceship = SpaceshipServiceImpl.getInstance().findAllAvailableShips().get(i);
        SpaceshipServiceImpl.getInstance().findAllAvailableShips().remove(spaceship);
        Collection<CrewMember> crewMembers = NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class);
        for (int u = 0; u < spaceship.getCrew().size(); u++) {
            crewMembers.add(CrewServiceImpl.getInstance().findAllAvailableCrewMembers().get(u));
        }
        try {
            MissionsServiceImpl.getInstance().createMission(name, start, end, distance, spaceship, crewMembers);
        } catch (DuplicateException e) {
            LOGGER.error("Such a mission already exists");
            ApplicationMenu.printAvailableOptions();
        }
        LOGGER.info("Mission was created");
    }
}