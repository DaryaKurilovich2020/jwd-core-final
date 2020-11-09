package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.ApplicationContext;
import com.epam.jwd.core_final.domain.*;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.factory.impl.SpaceShipFactory;
import com.epam.jwd.core_final.util.IdCounter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

// todo
public class NassaContext implements ApplicationContext {

    private static NassaContext nassaContext;
    private NassaContext(){

    }
    public static NassaContext getInstance(){
        if(nassaContext==null){
            nassaContext=new NassaContext();
        }
        return nassaContext;
    }
    private static final String SEPARATOR = FileSystems.getDefault().getSeparator();

    // no getters/setters for them
    private Collection<CrewMember> crewMembers = new ArrayList<>();
    private Collection<Spaceship> spaceships = new ArrayList<>();
    private Collection<FlightMission> flightMissions = new ArrayList<>();

    @Override
    public <T extends BaseEntity> Collection<T> retrieveBaseEntityList(Class<T> tClass) {
        return null;
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

        try (BufferedReader crewReader = new BufferedReader(new FileReader(crewFilePath));
             BufferedReader spaceshipReader = new BufferedReader(new FileReader(spaceshipsFilePath))) {
            hireCrewMembers(crewReader);
            buildSpaceships(spaceshipReader);
        } catch (IOException e) {
            throw new InvalidStateException();
        }
    }

    private String getInputRootDirPathWithSeparator(ApplicationProperties applicationProperties) {
        return applicationProperties.getInputRootDir() + SEPARATOR;
    }

    private void buildSpaceships(BufferedReader spaceshipReader) throws IOException {
        String[] spaceshipsAsString = spaceshipReader.readLine().split(";");
        Arrays.stream(spaceshipsAsString).forEach(s -> {
            SpaceShipFactory spaceShipFactory = new SpaceShipFactory();
            Spaceship spaceship = spaceShipFactory.create(IdCounter.getId(), s.split(","));
            spaceships.add(spaceship);
        });
    }

    private void hireCrewMembers(BufferedReader crewReader) throws IOException {
        String[] crewMembersAsString = crewReader.readLine().split(";");
        Arrays.stream(crewMembersAsString).forEach(s -> {
            CrewMemberFactory crewMemberFactory = new CrewMemberFactory();
            CrewMember crewMember = crewMemberFactory.create(IdCounter.getId(), s.split(","));
            crewMembers.add(crewMember);
        });
    }
}