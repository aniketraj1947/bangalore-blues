package com.sleek.application.repository;

import com.sleek.application.model.Registration;
import com.sleek.application.model.RegistrationReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    List<RegistrationReport> registrationReport();
}
