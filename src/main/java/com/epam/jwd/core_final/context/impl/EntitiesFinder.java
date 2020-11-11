package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.criteria.CrewMemberCriteria;
import com.epam.jwd.core_final.criteria.Criteria;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionsServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

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
                findById();
                findByName();
                findByRole();
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

    static void findById() {
        System.out.println("Let's find CrewMember by id");
        long id = 0;
        System.out.println("Enter id");
        try {
            id = scanner.nextInt();
        } catch (InputMismatchException e) {
            LOGGER.error("wrong input.Try again");
            findById();
        }
        Criteria<CrewMember> criteria = new CrewMemberCriteria();
        criteria.id(id);
        Optional<CrewMember> crewMember = CrewServiceImpl.getInstance().findCrewMemberByCriteria(criteria);
        if (crewMember.isPresent()) {
            System.out.println(crewMember.toString());
            LOGGER.info("CrewMember was found");
        } else {
            LOGGER.info("No CrewMember with this id");
        }
    }

    static void findByName() {
        System.out.println("Let's find CrewMember by name");
        String name;
        System.out.println("Enter name");
        name = scanner.next();
        Criteria<CrewMember> criteria = new CrewMemberCriteria();
        criteria.name(name);
        Optional<CrewMember> crewMember = CrewServiceImpl.getInstance().findCrewMemberByCriteria(criteria);
        if (crewMember.isPresent()) {
            System.out.println(crewMember.toString());
            LOGGER.info("CrewMember was found");
        } else {
            LOGGER.info("No CrewMember with this name");
        }
    }
    static void findByRole(){
        System.out.println("Let's find all CrewMembers by role");
        String name = "";
        System.out.println("Choose role:" + "\n" +
                "1.MISSION_SPECIALIST\n" +
                "2.FLIGHT_ENGINEER\n" +
                "3.PILOT\n" +
                "4.COMMANDER");
        int roleNumber = scanner.nextInt();
        Role role = Role.MISSION_SPECIALIST;
        switch (roleNumber) {
            case 1 -> role = Role.MISSION_SPECIALIST;
            case 2 -> role = Role.FLIGHT_ENGINEER;
            case 3 -> role = Role.PILOT;
            case 4 -> role = Role.COMMANDER;
            default -> {
                LOGGER.error("Wrong input.Try again");
                findByRole();
            }
        }
        CrewMemberCriteria criteria = new CrewMemberCriteria();
        criteria.role(role);
        List<CrewMember> crewMembers = CrewServiceImpl.getInstance().findAllCrewMembersByCriteria(criteria);
        if (crewMembers.size()!=0) {
            for (CrewMember crewMember:crewMembers) {
                System.out.println(crewMember.toString());
            }
            LOGGER.info("CrewMembers was found");
        } else {
            LOGGER.info("No CrewMembers with this role");
        }
    }
}
