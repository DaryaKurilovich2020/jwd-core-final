package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionsServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringJoiner;

public interface EntitiesFinder {
    Scanner scanner = new Scanner(System.in);
    Logger LOGGER = LoggerFactory.getLogger(EntitiesFinder.class);

    static void find() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("1.Find all CrewMembers");
        stringJoiner.add("2.Find CrewMembers by all criterias");
        stringJoiner.add("3.Find all missions");
        stringJoiner.add("4.Find all spaceships");
        stringJoiner.add("0.Back to menu");
        System.out.println(stringJoiner.toString());
        try {
            int input = scanner.nextInt();
            handleInput(input);
        } catch (InputMismatchException e) {
            LOGGER.error("Wrong input.Try again");
            find();
        }
    }

    static void handleInput(int input) {
        switch (input) {
            case 1:
                CrewServiceImpl.getInstance().findAllCrewMembers()
                        .forEach(crewMember -> System.out.println(crewMember.toString()));
            case 2:


            case 3:
                MissionsServiceImpl.getInstance().findAllMissions()
                        .forEach(flightMission -> System.out.println(flightMission.toString()));
            case 4:
                SpaceshipServiceImpl.getInstance().findAllSpaceships()
                    .forEach(spaceship -> System.out.println(spaceship.toString()));
            case 0:
                ApplicationMenu.printAvailableOptions();
                break;
            default:
                LOGGER.error("Wrong input.Try again");
                find();
        }
    }
}
