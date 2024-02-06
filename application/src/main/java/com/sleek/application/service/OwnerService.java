package com.sleek.application.service;

import com.sleek.model.Owner;
import org.springframework.stereotype.Service;

@Service
public interface OwnerService {
    Owner save(Owner user);

    Owner getOwnerById(String id);
}
