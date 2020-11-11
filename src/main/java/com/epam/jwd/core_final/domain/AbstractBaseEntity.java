package com.epam.jwd.core_final.domain;

/**
 * Expected fields:
 * <p>
 * id {@link Long} - entity id
 * name {@link String} - entity name
 */
public abstract class AbstractBaseEntity implements BaseEntity {

    private final Long id;
    private final String name;

    protected AbstractBaseEntity(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    @Override
    public Long getId() {
        //todo
        return this.id;
    }

    @Override
    public String getName() {
        // todo
        return this.name;
    }

    @Override
    public String toString() {
        return "Name:"+ this.name+"\n"+
                "Id:"+this.id;
    }
}
