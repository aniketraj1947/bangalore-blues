package com.sleek.application.controller;

import com.sleek.application.model.Registration;
import com.sleek.application.model.RegistrationReport;
import com.sleek.application.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("registration")
    public String getRegistration(@ModelAttribute ("registration")Registration registration) {
        return "registration";
    }

    @GetMapping("registrations")
    public @ResponseBody
    List<Registration>
    getRegistrations() {
        List<Registration> registrations = registrationService.findAll();
        return registrations;
    }

    @GetMapping("registration-reports")
    public @ResponseBody
    List<RegistrationReport>
    getRegistrationReports() {
        List<RegistrationReport> registrationReports = registrationService.findAllReports();
        return registrationReports;
    }

    @PostMapping("registration")
    public String addRegistration(@ModelAttribute ("registration")
                                              Registration registration,
                                  BindingResult result) {

        if(result.hasErrors()) {
            System.out.println("There were errors");
            return "registration";
        } else {
            registrationService.addRegistration(registration);
        }

        System.out.println("Registration: " + registration.getName());

        return "redirect:registration";
    }

    @PostMapping("registration/update")
    public @ResponseBody Registration updateRegistration (@ModelAttribute("registration")
                                                          Registration registration,
                                                          BindingResult result) {
        return registrationService.addRegistration(registration);
    }
}
