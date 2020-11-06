package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.factory.EntityFactory;
import com.epam.jwd.core_final.factory.impl.CrewMemberFactory;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {

    private Role role;
    private Rank rank;

    @Override
    public CrewMemberCriteria id(Long id) {
        return (CrewMemberCriteria) super.id(id);
    }

    @Override
    public CrewMemberCriteria name(String name) {
        return (CrewMemberCriteria) super.name(name);
    }

    public CrewMemberCriteria role(Role role) {
        this.role = role;
        return this;
    }

    public CrewMemberCriteria rank(Rank rank) {
        this.rank = rank;
        return this;
    }

    @Override
    public CrewMember build() {
        EntityFactory<CrewMember> crewMemberFactory = new CrewMemberFactory();
        return crewMemberFactory.create(this.id, this.name, this.role, this.rank);
    }
}
