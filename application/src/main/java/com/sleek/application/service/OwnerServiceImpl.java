package com.sleek.application.service;

import com.sleek.application.repository.OwnerRepository;
import com.sleek.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }

    @Override
    public Owner getOwnerById(String ownerId) {
        final Optional<Owner> ownerOpt = ownerRepository.findById(ownerId);
        return ownerOpt.orElse(null);
    }

}
