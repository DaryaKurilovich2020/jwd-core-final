package com.epam.jwd.core_final.context;

import java.util.StringJoiner;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    //можно сделать статик, а так же в принт можно засунуть аргументы
    default String printAvailableOptions() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("1.Create mission");
        stringJoiner.add("2.View all crewmembres");
        stringJoiner.add("3.View all spaceships");
        stringJoiner.add("4.Write crewmembers to JSON");
        stringJoiner.add("5.Write spaceships to JSON");
        stringJoiner.add("0.Close application");
        return stringJoiner.toString();
    }

    default void handleUserInput(String input) {
        switch (input) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                break;
            default:
                break;
        }
    }
}
