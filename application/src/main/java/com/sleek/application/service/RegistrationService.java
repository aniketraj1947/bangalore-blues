package com.sleek.application.service;

import com.sleek.application.model.Registration;
import com.sleek.application.model.RegistrationReport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegistrationService {
    Registration addRegistration(Registration registration);

    List<Registration> findAll();

    List<RegistrationReport> findAllReports();
}
