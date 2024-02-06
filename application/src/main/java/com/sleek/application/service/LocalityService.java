package com.sleek.application.service;

import com.sleek.model.Locality;
import org.springframework.stereotype.Service;

@Service
public interface LocalityService {

    Locality save(Locality locality);
}
