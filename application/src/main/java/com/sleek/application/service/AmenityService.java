package com.sleek.application.service;

import com.sleek.model.Amenity;
import org.springframework.stereotype.Service;

@Service
public interface AmenityService {

    Amenity save(Amenity amenity);
}
