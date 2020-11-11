package com.epam.jwd.core_final.util;

import com.epam.jwd.core_final.domain.ApplicationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class PropertyReaderUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertyReaderUtil.class);
    private static final Properties properties = new Properties();

    private PropertyReaderUtil() {
    }

    public static Properties getProperties() {
        loadProperties();
        return properties;
    }


    /**
     * try-with-resource using FileInputStream
     *
     * @see {https://www.netjstech.com/2017/09/how-to-read-properties-file-in-java.html for an example}
     * <p>
     * as a result - you should populate {@link ApplicationProperties} with corresponding
     * values from property file
     */
    private static void loadProperties() {
        final String propertiesFileName = "resource/application.properties";
        try (FileInputStream fileInputStream = new FileInputStream(propertiesFileName)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.error("Properties file does notФ exist");
            e.printStackTrace();
        }
    }
}