package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.EntitiesFinder;
import com.epam.jwd.core_final.context.impl.EntitiesUpdater;
import com.epam.jwd.core_final.context.impl.JSONWriter;
import com.epam.jwd.core_final.context.impl.MissionCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import java.util.StringJoiner;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {
    Logger LOGGER = LoggerFactory.getLogger(ApplicationMenu.class);

    ApplicationContext getApplicationContext();

    Scanner scanner = new Scanner(System.in);

    static void printAvailableOptions() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("1.Create mission");
        stringJoiner.add("2.Find and view entities");
        stringJoiner.add("3.Write entities to JSON");
        stringJoiner.add("4.Update entities");
        stringJoiner.add("0.Close application");
        System.out.println(stringJoiner.toString());
        handleUserInput(Integer.parseInt(scanner.next()));
    }

    static void handleUserInput(int input) {
        switch (input) {
            case 0:
                System.exit(0);
                LOGGER.info("Exit");
            case 1:
                MissionCreator.createMission();
                break;
            case 2:
                EntitiesFinder.find();
                break;
            case 3:
                JSONWriter.write();
                break;
            case 4:
                EntitiesUpdater.update();
                break;
        }
    }

}
