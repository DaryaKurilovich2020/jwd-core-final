package com.epam.jwd.core_final.domain;

import com.epam.jwd.core_final.util.PropertyReaderUtil;

import java.util.Properties;

/**
 * This class should be IMMUTABLE!
 * <p>
 * Expected fields:
 * <p>
 * inputRootDir {@link String} - base dir for all input files
 * outputRootDir {@link String} - base dir for all output files
 * crewFileName {@link String}
 * missionsFileName {@link String}
 * spaceshipsFileName {@link String}
 * <p>
 * fileRefreshRate {@link Integer}
 * dateTimeFormat {@link String} - date/time format for {@link java.time.format.DateTimeFormatter} pattern
 */
public final class ApplicationProperties {

    private static final String INPUT_ROOT_DIR = "inputRootDir";
    private static final String OUTPUT_ROOT_DIR = "outputRootDir";
    private static final String CREW_FILE_NAME = "crewFileName";
    private static final String MISSION_FILE_NAME = "missionFileName";
    private static final String SPACESHIP_FILE_NAME = "spaceShipFileName";
    private static final String FILE_REFRESH_RATE = "fileRefreshRate";
    private static final String DATE_TIME_FORMAT = "dateTimeFormat";

    private String inputRootDir;
    private String outputRootDir;
    private String crewFileName;
    private String missionsFileName;
    private String spaceshipsFileName;
    private Integer fileRefreshRate;
    private String dateTimeFormat;

    private ApplicationProperties() {
    }

    public static ApplicationProperties getInstance() {
        Properties properties = PropertyReaderUtil.getProperties();
        ApplicationProperties applicationProperties = new ApplicationProperties();
        applicationProperties.inputRootDir = properties.getProperty(INPUT_ROOT_DIR);
        applicationProperties.outputRootDir = properties.getProperty(OUTPUT_ROOT_DIR);
        applicationProperties.crewFileName = properties.getProperty(CREW_FILE_NAME);
        applicationProperties.missionsFileName = properties.getProperty(MISSION_FILE_NAME);
        applicationProperties.spaceshipsFileName = properties.getProperty(SPACESHIP_FILE_NAME);
        applicationProperties.fileRefreshRate = Integer.valueOf(properties.getProperty(FILE_REFRESH_RATE));
        applicationProperties.dateTimeFormat = properties.getProperty(DATE_TIME_FORMAT);
        return applicationProperties;
    }

    public String getInputRootDir() {
        return inputRootDir;
    }

    public String getOutputRootDir() {
        return outputRootDir;
    }

    public String getCrewFileName() {
        return crewFileName;
    }

    public String getMissionsFileName() {
        return missionsFileName;
    }

    public String getSpaceshipsFileName() {
        return spaceshipsFileName;
    }

    public Integer getFileRefreshRate() {
        return fileRefreshRate;
    }

    public String getDateTimeFormat() {
        return dateTimeFormat;
    }
}
