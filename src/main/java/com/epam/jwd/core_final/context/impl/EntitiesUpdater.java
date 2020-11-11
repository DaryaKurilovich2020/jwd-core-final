package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.DuplicateException;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringJoiner;

public interface EntitiesUpdater {
    Scanner scanner = new Scanner(System.in);
    Logger LOGGER = LoggerFactory.getLogger(EntitiesUpdater.class);

    static void update() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("1.Update CrewMember");
        stringJoiner.add("2.Update Spaceship");
        stringJoiner.add("3.Update Mission");
        stringJoiner.add("0.Back to main menu");
        System.out.println(stringJoiner.toString());
        try {
            int input = scanner.nextInt();
            handleInput(input);
        } catch (InputMismatchException | DuplicateException e) {
            LOGGER.warn("Wrong input.Try again");
            update();
        }
    }

    static void handleInput(int input) throws DuplicateException {
        switch (input) {
            case 0:
                ApplicationMenu.printAvailableOptions();
                break;
            case 1:
                updateCrewMember();
            case 2:
                updateSpaceship();
            case 3:
                updateMission();
            default:
                LOGGER.warn("Wrong input.Try again");
        }
    }

    static void updateCrewMember() throws DuplicateException {
        System.out.println("Choose CrewMember");
        int i = 1;
        for (CrewMember crewMember : CrewServiceImpl.getInstance().findAllCrewMembers()) {
            System.out.println(i + " " + crewMember.getName());
        }
        String name = "";
        try {
            int crewNumber = scanner.nextInt();
            name = CrewServiceImpl.getInstance().findAllCrewMembers().get(crewNumber).getName();
        } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
            LOGGER.error("Wrong CrewMember number.Try again");
            updateCrewMember();
        }
        System.out.println("Choose rank:" +
                "1.TRAINEE\n" +
                "2.SECOND_OFFICER(2L)\n" +
                "3.FIRST_OFFICER(3L)\n" +
                "4.CAPTAIN");
        int rankNumber = scanner.nextInt();
        Rank rank = Rank.TRAINEE;
        switch (rankNumber) {
            case 1 -> rank = Rank.TRAINEE;
            case 2 -> rank = Rank.SECOND_OFFICER;
            case 3 -> rank = Rank.FIRST_OFFICER;
            case 4 -> rank = Rank.CAPTAIN;
            default -> {
                LOGGER.error("Wrong input.Try again");
                updateCrewMember();
            }
        }
        System.out.println("Choose role:" + "\n" +
                "1.MISSION_SPECIALIST(1L)\n" +
                "2.FLIGHT_ENGINEER(2L)\n" +
                "3.PILOT(3L)\n" +
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
                updateCrewMember();
            }
        }
        CrewMember crewMember = CrewServiceImpl.getInstance().createCrewMember(name, role, rank);
        CrewServiceImpl.getInstance().updateCrewMemberDetails(crewMember);
    }

    static void updateMission() {

    }

    static void updateSpaceship() {

    }
}
