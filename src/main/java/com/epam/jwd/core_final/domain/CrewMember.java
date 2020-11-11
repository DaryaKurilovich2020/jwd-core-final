package com.epam.jwd.core_final.domain;

import java.util.StringJoiner;

/**
 * Expected fields:
 * <p>
 * role {@link Role} - member role
 * rank {@link Rank} - member rank
 * isReadyForNextMissions {@link Boolean} - true by default. Set to false, after first failed mission
 */
public class CrewMember extends AbstractBaseEntity {

    private Role role;
    private Rank rank;
    private boolean isReadyForNextMission;

    public CrewMember(Long id, String name) {
        super(id, name);
    }

    public void setReadyForNextMission(boolean readyForNextMission) {
        isReadyForNextMission = readyForNextMission;
    }

    public Role getRole() {
        return role;
    }

    public Rank getRank() {
        return rank;
    }

    public boolean isReadyForNextMission() {
        return isReadyForNextMission;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner("\n");
        stringJoiner.add(super.toString());
        stringJoiner.add("Rank:" + this.getRank());
        stringJoiner.add("Role" + this.getRole());
        return stringJoiner.toString();
    }
}
