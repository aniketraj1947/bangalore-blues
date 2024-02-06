package com.sleek.application.repository;

import com.sleek.model.Owner;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public abstract class OwnerRepositoryImpl implements OwnerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Owner save(Owner owner) {
        entityManager.persist(owner);
        return owner;
    }

}
