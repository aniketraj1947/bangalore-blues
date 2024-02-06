package com.sleek.application.service;

import com.sleek.application.repository.AmenityRepository;
import com.sleek.model.Amenity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AmentiyServiceImpl {
    @Autowired
    private AmenityRepository amenityRepository;

    public Amenity save(final Amenity amenity) {
        return amenityRepository.save(amenity);
    }
}
