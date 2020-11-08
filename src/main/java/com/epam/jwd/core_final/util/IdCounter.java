package com.epam.jwd.core_final.util;

public final class IdCounter {
    private static Long id;

    public static Long getId() {
        return id++;
    }
}
