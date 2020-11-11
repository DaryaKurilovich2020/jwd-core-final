package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.context.EntityFileReader;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.SpaceShipFactory;
import com.epam.jwd.core_final.util.IdCounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

// todo
public class NassaContext implements ApplicationContext {
    private static NassaContext nassaContext;

    private NassaContext() {

    }

    public static NassaContext getInstance() {
        if (nassaContext == null) {
            nassaContext = new NassaContext();
        }
        return nassaContext;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(NassaContext.class);
    private static final String SEPARATOR = FileSystems.getDefault().getSeparator();

    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<FlightMission> flightMissions = new ArrayList<>();

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        return switch (tClass.getSimpleName()) {
            case "CrewMember" -> (Collection<T>) crewMembers;
            case "Spaceship" -> (Collection<T>) spaceships;
            case "FlightMission" -> (Collection<T>) flightMissions;
            default -> null;
        };
    }

    /**
     * You have to read input files, populate collections
     *
     * @throws InvalidStateException
     */
    @Override
    public void init() throws InvalidStateException {
        ApplicationProperties properties = ApplicationProperties.getInstance();
        String crewFilePath = getInputRootDirPathWithSeparator(properties) + SEPARATOR + properties.getCrewFileName();
        String spaceshipsFilePath = getInputRootDirPathWithSeparator(properties) + properties.getSpaceshipsFileName();

        try (BufferedReader crewReader = new BufferedReader(new FileReader(crewFilePath))) {
            EntityFileReader crewFileReader = new CrewFileReader();
            crewFileReader.read(crewReader);
        } catch (IOException e) {
            LOGGER.error("CrewMember file not found");
            throw new InvalidStateException();
        }
        try (BufferedReader spaceshipReader = new BufferedReader(new FileReader(spaceshipsFilePath))) {
            EntityFileReader spaceshipsFileReader = new SpaceshipFileReader();
            spaceshipsFileReader.read(spaceshipReader);

        } catch (IOException e) {
            LOGGER.error("Spaceships file not found");
            e.printStackTrace();
        }
    }

    private String getInputRootDirPathWithSeparator(ApplicationProperties applicationProperties) {
        return applicationProperties.getInputRootDir() + SEPARATOR;
    }
}