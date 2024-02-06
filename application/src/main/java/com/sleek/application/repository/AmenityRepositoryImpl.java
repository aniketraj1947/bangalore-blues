package com.sleek.application.repository;

import com.sleek.model.Amenity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public abstract class AmenityRepositoryImpl implements AmenityRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Amenity save(final Amenity amenity) {
        entityManager.persist(amenity);
        return amenity;
    }
}
