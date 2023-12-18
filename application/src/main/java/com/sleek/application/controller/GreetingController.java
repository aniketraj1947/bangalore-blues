package com.sleek.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class GreetingController {

    @GetMapping("greeting")
    public String greeting (Map<String, Object> model) {
        model.put("message", "Hello there");
        return "Bangalore Blue Project Details";
    }

    @PostMapping("processLocality")
    public String processLocality(@RequestParam("selectedLocality") String selectedLocality) {
        return "You selected" + selectedLocality;
    }

}
