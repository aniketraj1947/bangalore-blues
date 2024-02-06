package com.sleek.application.repository;

import com.sleek.model.Property;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public abstract class PropertyRepositoryImpl implements PropertyRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Property save(final Property property) {
        entityManager.persist(property);
        return property;
    }
}
