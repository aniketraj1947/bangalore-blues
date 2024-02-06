package com.sleek.application.repository;

import com.sleek.model.Locality;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class LocalityRepositoryImpl implements LocalityRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Locality save(final Locality locality) {
        entityManager.persist(locality);
        return locality;
    }
}
