package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.exception.InvalidStateException;

import java.util.function.Supplier;

public interface Application {
    //сделать поле apl menu в и старте запустить метод принт options
    static void start() throws InvalidStateException {
        final Supplier<ApplicationContext> applicationContextSupplier = NassaContext::new;// todo
        final NassaContext nassaContext = new NassaContext();
        nassaContext.init();
        afterContextInit(applicationContextSupplier::get);
    }

    private static void afterContextInit(ApplicationMenu applicationMenu) {
        applicationMenu.printAvailableOptions();
    }
}
