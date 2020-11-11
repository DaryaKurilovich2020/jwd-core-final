package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.service.MissionService;
import com.epam.jwd.core_final.service.impl.MissionsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringJoiner;

public interface EntitiesUpdater {
    Scanner scanner=new Scanner(System.in);
    Logger LOGGER= LoggerFactory.getLogger(EntitiesUpdater.class);
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
        }
        catch (InputMismatchException e){
            LOGGER.warn("Wrong input.Try again");
            update();
        }
    }
    static void handleInput(int input){
switch (input){
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
    static void updateCrewMember(){
        int i=1;
        for (FlightMission mission: MissionsServiceImpl.getInstance().findAllMissions()) {
            System.out.println(i+" "+mission.getName());
        }
        System.out.println();
    }
    static void updateMission(){

    }
    static void updateSpaceship(){

    }
}
