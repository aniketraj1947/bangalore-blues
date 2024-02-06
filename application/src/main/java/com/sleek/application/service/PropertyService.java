package com.sleek.application.service;

import com.sleek.model.Property;
import org.springframework.stereotype.Service;

@Service
public interface PropertyService {

    Property save(Property property);

}
