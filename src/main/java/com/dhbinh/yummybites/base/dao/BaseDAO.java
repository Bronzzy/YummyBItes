package com.dhbinh.yummybites.base.dao;

import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Contract for a generic DAO.
 *
 * @param <E> - Entity type parameter.
 */
@NoArgsConstructor
public abstract class BaseDAO<E> {
    @PersistenceContext(name = "yummybites")
    protected EntityManager em;

    private Class<E> entityClass;

    public E create(E entity) {
        this.em.persist(entity);
        return entity;
    }

}
