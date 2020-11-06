package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyReaderUtil {

    private static final Properties properties = new Properties();

    private PropertyReaderUtil() {
    }

    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */
    public static void loadProperties() {
        final String propertiesFileName = "resource/application.properties";
        InputStream inputStream = null;
        try (FileInputStream fileInputStream = new FileInputStream(propertiesFileName)) {
            inputStream = fileInputStream;
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void readProperties() {
        ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
        applicationProperties.setInputRootDir(properties.getProperty("inputRootDir"));
        applicationProperties.setOutputRootDir(properties.getProperty("outputRootDir"));
        applicationProperties.setCrewFileName(properties.getProperty("crewFileName"));
        applicationProperties.setMissionsFileName(properties.getProperty("missionsFileName"));
        applicationProperties.setSpaceshipsFileName(properties.getProperty("spaceshipsFileName"));
        applicationProperties.setFileRefreshRate(Integer.parseInt(properties.getProperty("fileRefreshRate")));
        applicationProperties.setSpaceshipsFileName(properties.getProperty("spaceshipsFileName"));
        applicationProperties.setDateTimeFormat(properties.getProperty("dateTimeFormat"));
    }
}