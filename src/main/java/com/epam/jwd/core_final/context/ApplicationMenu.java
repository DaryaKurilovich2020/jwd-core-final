package com.epam.jwd.core_final.context;

// todo replace Object with your own types
@FunctionalInterface
public interface ApplicationMenu {

    ApplicationContext getApplicationContext();

    //можно сделать статик, а так же в принт можно засунуть аргументы
    default String printAvailableOptions() {
        String availableOptions = "1.Create mission\n" +
                "2.View all crewmembres\n" +
                "3.View all spaceships\n" +
                "4.Print crewmembers to JSON\n" +
                "5.Print spaceships to JSON\n" +
                "6.Close application";
        return availableOptions;
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
