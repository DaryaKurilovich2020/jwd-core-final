package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationMenu;
import com.epam.jwd.core_final.domain.ApplicationProperties;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.FlightMission;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.service.impl.CrewServiceImpl;
import com.epam.jwd.core_final.service.impl.MissionsServiceImpl;
import com.epam.jwd.core_final.service.impl.SpaceshipServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public interface JSONWriter {
    Logger LOGGER = LoggerFactory.getLogger(JSONWriter.class);
    Scanner scanner = new Scanner(System.in);

    static void write() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add("1.Write CrewMembers");
        stringJoiner.add("2.Write spaceships");
        stringJoiner.add("3.Write missions");
        stringJoiner.add("0.Back to menu");
        System.out.println(stringJoiner.toString());
        try {
            int input = scanner.nextInt();
            handleInput(input);
        } catch (InputMismatchException e) {
            LOGGER.error("Wrong input.Try again");
            write();
        }
    }

    static void handleInput(int input) {
        String outputPath = ApplicationProperties.getInstance().getOutputRootDir();
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        switch (input) {
            case 0 -> ApplicationMenu.printAvailableOptions();
            case 1 -> {
                List<CrewMember> crewMembers = CrewServiceImpl.getInstance().findAllCrewMembers();
                try (FileOutputStream fileOutputStream = new FileOutputStream(outputPath + "crewmember.json")) {
                    objectMapper.writeValue(fileOutputStream, crewMembers);
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                }
                LOGGER.info("All CrewMembers were written to JSON file");
            }
            case 2 -> {
                List<FlightMission> missions = MissionsServiceImpl.getInstance().findAllMissions();
                try (FileOutputStream fileOutputStream = new FileOutputStream(outputPath + "mission.json")) {
                    objectMapper.writeValue(fileOutputStream, missions);
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                }
                LOGGER.info("All missions were written to JSON file");
            }
            case 3 -> {
                List<Spaceship> spaceships = SpaceshipServiceImpl.getInstance().findAllSpaceships();
                try (FileOutputStream fileOutputStream = new FileOutputStream(outputPath + "spaceship.json")) {
                    objectMapper.writeValue(fileOutputStream, spaceships);
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                    e.printStackTrace();
                }
                LOGGER.info("All spaceships were written to JSON file");
            }
            default -> {
                LOGGER.error("Wrong input.Try again.Choose an option");
                write();
            }
        }
    }
}
