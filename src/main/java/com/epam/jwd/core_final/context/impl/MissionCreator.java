package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.impl.MissionsServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;

import java.time.LocalDateTime;
import java.util.Scanner;

public interface MissionCreator {
    Scanner scanner = ApplicationMenu.scanner;

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
        printShips();
        int i = Integer.parseInt(scanner.next());
        Spaceship spaceship=SpaceshipServiceImpl.getInstance().findAllAvailableShips().get(i);
        SpaceshipServiceImpl.getInstance().findAllAvailableShips().remove(spaceship);
        MissionsServiceImpl.getInstance().createMission(name, start, end, distance,spaceship,);
    }

    static void printShips() {
        for (int i = 0; i < SpaceshipServiceImpl.getInstance().findAllAvailableShips().size(); i++) {
            System.out.println(i + 1 + SpaceshipServiceImpl.getInstance().findAllAvailableShips().get(i).getName());
        }
    }
}