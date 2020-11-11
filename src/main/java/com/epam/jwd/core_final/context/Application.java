package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface Application {
    Logger LOGGER= LoggerFactory.getLogger(Application.class);
    static void start() throws InvalidStateException {
            NassaContext.getInstance().init();
        LOGGER.info("init successfully completed");
        ApplicationMenu.printAvailableOptions();
    }
}