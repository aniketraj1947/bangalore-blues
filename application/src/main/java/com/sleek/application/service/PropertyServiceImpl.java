package com.sleek.application.service;

import com.sleek.application.repository.PropertyRepository;
import com.sleek.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Override
    public Property save(final Property property) {
        return propertyRepository.save(property);
    }
}
