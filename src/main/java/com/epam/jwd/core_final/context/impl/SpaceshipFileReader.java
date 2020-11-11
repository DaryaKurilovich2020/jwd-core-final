package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.EntityFileReader;
import com.epam.jwd.core_final.domain.Spaceship;
import com.epam.jwd.core_final.factory.impl.SpaceShipFactory;
import com.epam.jwd.core_final.util.IdCounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class SpaceshipFileReader implements EntityFileReader {
    @Override
    public void read(BufferedReader spaceshipReader) throws IOException {
        String[] spaceshipsAsString = spaceshipReader.readLine().split(";");
        Arrays.stream(spaceshipsAsString).forEach(s -> {
            SpaceShipFactory spaceShipFactory = new SpaceShipFactory();
            Spaceship spaceship = spaceShipFactory.create(IdCounter.getId(), s.split(","));
            NassaContext.getInstance().retrieveBaseEntityList(Spaceship.class).add(spaceship);
        });
    }
}