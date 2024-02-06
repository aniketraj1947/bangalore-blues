package com.sleek.application.service;

import com.sleek.application.repository.LocalityRepository;
import com.sleek.model.Locality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LocalityServiceImpl implements LocalityService {

    @Autowired
    private LocalityRepository localityRepository;

    @Override
    public Locality save(final Locality locality) {
        return localityRepository.save(locality);
    }
}
