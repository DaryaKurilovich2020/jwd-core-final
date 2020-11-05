package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {
    protected final Long id;
    protected final String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    protected Criteria(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static abstract class CriteriaBuilder {
        protected Long id;
        protected String name;

        public CriteriaBuilder() {

        }

        public CriteriaBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public CriteriaBuilder name(String name) {
            this.name = name;
            return this;
        }

        public abstract Criteria build();
    }
}