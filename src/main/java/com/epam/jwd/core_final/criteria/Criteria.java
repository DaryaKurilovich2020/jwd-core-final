package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {

    protected Long id;
    protected String name;

    public Criteria<T> id(Long id) {
        this.id = id;
        return this;
    }

    public Criteria<T> name(String name) {
        this.name = name;
        return this;
    }

    public abstract T build();
}