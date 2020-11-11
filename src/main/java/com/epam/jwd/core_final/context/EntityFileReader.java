package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;

import java.io.BufferedReader;
import java.io.IOException;

public interface EntityFileReader {
    void read(BufferedReader bufferedReader) throws IOException;

}
