package com.sleek.application.service;

import com.sleek.application.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User save(User user);
}
