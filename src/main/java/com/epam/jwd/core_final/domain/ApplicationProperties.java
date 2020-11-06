package com.epam.jwd.core_final.domain;

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

    private String inputRootDir;
    private String outputRootDir;
    private String crewFileName;
    private String missionsFileName;
    private String spaceshipsFileName;
    private Integer fileRefreshRate;
    private String dateTimeFormat;
    private static ApplicationProperties applicationProperties;

    public static ApplicationProperties getInstance() {
        if (applicationProperties==null){
            applicationProperties=new ApplicationProperties();
        }
        return applicationProperties;
    }

    public void setFileRefreshRate(Integer fileRefreshRate) {
        if (this.fileRefreshRate == null) {
            this.fileRefreshRate = fileRefreshRate;
        }
    }

    public void setDateTimeFormat(String dateTimeFormat) {
        if (this.dateTimeFormat == null) {
            this.dateTimeFormat = dateTimeFormat;
        }
    }

    public void setInputRootDir(String inputRootDir) {
        if (this.inputRootDir == null) {
            this.inputRootDir = inputRootDir;
        }
    }

    public void setOutputRootDir(String outputRootDir) {
        if (this.outputRootDir == null) {
            this.outputRootDir = outputRootDir;
        }
    }

    public void setCrewFileName(String crewFileName) {
        if (this.crewFileName == null) {
            this.crewFileName = crewFileName;
        }
    }

    public void setMissionsFileName(String missionsFileName) {
        if (this.missionsFileName == null) {
            this.missionsFileName = missionsFileName;
        }
    }

    public void setSpaceshipsFileName(String spaceshipsFileName) {
        if (this.spaceshipsFileName == null) {
            this.spaceshipsFileName = spaceshipsFileName;
        }
    }
}
