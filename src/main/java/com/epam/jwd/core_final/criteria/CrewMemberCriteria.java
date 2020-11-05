package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;

/**
 * Should be a builder for {@link com.epam.jwd.core_final.domain.CrewMember} fields
 */
public class CrewMemberCriteria extends Criteria<CrewMember> {

    private final Role role;
    private final Rank rank;

    private CrewMemberCriteria(Long id, String name, Role role, Rank rank) {
        super(id, name);
        this.role = role;
        this.rank = rank;
    }

    public Role getRole() {
        return role;

    }

    public Rank getRank() {
        return rank;
    }

    private static class CrewMemberCriteriaBuilder extends CriteriaBuilder {

        private Role role;
        private Rank rank;

        public CrewMemberCriteriaBuilder() {
        }

        public CrewMemberCriteriaBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public CrewMemberCriteriaBuilder rank(Rank rank) {
            this.rank = rank;
            return this;
        }

        @Override
        public CrewMemberCriteria build() {
            return new CrewMemberCriteria(id, name, role, rank);
        }
    }
}
