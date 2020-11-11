package com.epam.jwd.core_final.context.impl;

import com.epam.jwd.core_final.context.EntityFileReader;
import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;
import com.epam.jwd.core_final.util.IdCounter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class CrewFileReader implements EntityFileReader {
    @Override
    public void read(BufferedReader crewReader) throws IOException {

        String[] crewMembersAsString = crewReader.readLine().split(";");
        Arrays.stream(crewMembersAsString).forEach(s -> {
            CrewMemberFactory crewMemberFactory = new CrewMemberFactory();
            CrewMember crewMember = crewMemberFactory.create(IdCounter.getId(), s.split(","));
            NassaContext.getInstance().retrieveBaseEntityList(CrewMember.class).add(crewMember);
        });
    }
}
