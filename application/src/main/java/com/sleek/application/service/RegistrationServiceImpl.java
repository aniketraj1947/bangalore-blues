package com.sleek.application.service;

import com.sleek.application.model.Course;
import com.sleek.application.model.Registration;
import com.sleek.application.model.RegistrationReport;
import com.sleek.application.repository.CourseRepository;
import com.sleek.application.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private CourseRepository courseRepository;


    @Override
    public Registration addRegistration(Registration registration) {
        registration = registrationRepository.save(registration);

        if(registration.getId() == null) {
            Course course = new Course();
            course.setName("Intro");
            course.setDescription("Every attendee must comple the intro.");
            course.setRegistration(registration);

            courseRepository.save(course);
        }

        return registration;
    }

    @Override
    public List<Registration> findAll() {
        return registrationRepository.findAll();
    }

    @Override
    public List<RegistrationReport> findAllReports() {
        return registrationRepository.registrationReport();
    }

}
