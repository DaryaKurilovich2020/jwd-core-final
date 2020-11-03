package com.epam.jwd.core_final.criteria;

import com.epam.jwd.core_final.domain.BaseEntity;

/**
 * Should be a builder for {@link BaseEntity} fields
 */
public abstract class Criteria<T extends BaseEntity> {
    private final Long id;
    private final String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private Criteria(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    private static class CriteriaBuilder {
        private Long id;
        private String name;

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

        public Criteria build() {
            return new Criteria(id, name);
        }
    }
}
